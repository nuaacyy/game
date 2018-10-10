import { all, call, fork, put, race, take, takeEvery } from 'redux-saga/effects';
import { client } from '../../client';
import {
    actionSucceeded,
    FailedAction,
    IReqAction,
    MARK_CREATE_MODIFY_PLATFORM,
    REQ_ADD_PLT,
    REQ_CHG_PLT,
    REQ_DEL_PLT,
    REQ_LOGIN,
    REQ_QUERY_PLT
} from '../ibaseaction';
import { IMarkCreateModifyPlatform } from '../../redux/modules/serverMgr/platform';
import { EXCEPTION_ERROR, REQ_EXCEPTION } from '../../constvalue';

export function markCreateOrModifyPlatform(atCreatingOrModifying: boolean): IMarkCreateModifyPlatform {
    return {
        type: MARK_CREATE_MODIFY_PLATFORM,
        atCreatingOrModifying,
    };
}

// 查询平台
export function* queryPlatformDeal() {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/queryPlat');
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_QUERY_PLT, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_QUERY_PLT), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_QUERY_PLT, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

export interface IAddPlatformData {
    platName: string;
    opId: number;
    gameId: number;
}

// 添加平台
export function addPlatform(platName: string, opId: number, gameId: number): IReqAction<IAddPlatformData> {
    return {
        type: REQ_ADD_PLT,
        payload: {
            platName,
            opId,
            gameId,
        },
    };
}

export function* addPlatformDeal(action: IReqAction<IAddPlatformData>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/addPlat', {
                params: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_ADD_PLT, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_ADD_PLT)});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_ADD_PLT, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 修改平台
export function chgPlatform(data: {}): IReqAction<{}> {
    return {
        type: REQ_CHG_PLT,
        payload: data,
    };
}

export function* changePlatformDeal(action: IReqAction<{}>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.post('/v1/updatePlat', {
                data: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_CHG_PLT, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_CHG_PLT)});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_CHG_PLT, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

export interface IDelPlatformData {
    id: number;
}

// 删除平台
export function deletePlatform(id: number): IReqAction<IDelPlatformData> {
    return {
        type: REQ_DEL_PLT,
        payload: {
            id,
        },
    };
}

export function* deletePlatformDeal(action: IReqAction<IDelPlatformData>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/delPlat', {
                params: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_DEL_PLT, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_DEL_PLT)});
        }
    } catch (e) {
        yield put(new FailedAction(REQ_DEL_PLT, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 在添加、修改、删除平台配置成功后触发更新
export function* refreshPlatformsDeal() {
    while (true) {
        yield race({
            login: take(actionSucceeded(REQ_LOGIN)),
            add: take(actionSucceeded(REQ_ADD_PLT)),
            del: take(actionSucceeded(REQ_DEL_PLT)),
            chage: take(actionSucceeded(REQ_CHG_PLT)),
        });

        // 触发查询
        yield put({type: REQ_QUERY_PLT});
    }
}

export function* platformMgrSaga() {
    yield all([
        takeEvery(REQ_QUERY_PLT, queryPlatformDeal),
        takeEvery(REQ_ADD_PLT, addPlatformDeal),
        takeEvery(REQ_CHG_PLT, changePlatformDeal),
        takeEvery(REQ_DEL_PLT, deletePlatformDeal),

        fork(refreshPlatformsDeal),
    ]);
}