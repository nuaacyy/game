import { all, call, fork, put, race, take, takeEvery } from 'redux-saga/effects';
import { client } from '../../client';
import {
    actionSucceeded,
    FailedAction,
    IBaseAction,
    IReqAction,
    MARK_AREA_CACHE_DIRTY,
    MARK_CREATE_MODIFY_AREA_WORLD,
    REQ_ADD_WORLD_AREA,
    REQ_CHG_WORLD_AREA,
    REQ_DEL_WORLD_AREA,
    REQ_LOGIN,
    REQ_QUERY_WORLD_AREA
} from '../ibaseaction';
import { IMarkCreateModifyAreaWorld } from '../../redux/modules/serverMgr/areaWorld';
import { EXCEPTION_ERROR, REQ_EXCEPTION } from '../../constvalue';

// 标记是否在创建或修改游戏区
export function createOrModifyAreaWorld(atCreatingOrModifying: boolean): IMarkCreateModifyAreaWorld {
    return {
        type: MARK_CREATE_MODIFY_AREA_WORLD,
        atCreatingOrModifying,
    };
}

export function markAreaCacheDirty(): IBaseAction {
    return {
        type: MARK_AREA_CACHE_DIRTY,
    };
}

function* queryAreaDeal() {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/queryWorldArea');
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_QUERY_WORLD_AREA, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_QUERY_WORLD_AREA), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_QUERY_WORLD_AREA, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

export function addArea(data: {}): IReqAction<{}> {
    return {
        type: REQ_ADD_WORLD_AREA,
        payload: data,
    };
}

function* addAreaDeal(action: IReqAction<{}>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.post('/v1/addGameArea', {
                data: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_ADD_WORLD_AREA, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_ADD_WORLD_AREA), payload: result.data, rt3: result.rt});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_ADD_WORLD_AREA, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

export function chgArea(data: {}): IReqAction<{}> {
    return {
        type: REQ_CHG_WORLD_AREA,
        payload: data,
    };
}

function* chgAreaDeal(action: IReqAction<{}>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.post('/v1/updateGameArea', {
                data: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_CHG_WORLD_AREA, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_CHG_WORLD_AREA), payload: result.data, rt2: result.rt});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_CHG_WORLD_AREA, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

export interface IDelAreaData {
    id: number;
}

export function deleteArea(id: number): IReqAction<IDelAreaData> {
    return {
        type: REQ_DEL_WORLD_AREA,
        payload: {
            id,
        },
    };
}

function* deleteAreaDeal(action: IReqAction<IDelAreaData>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/delGameArea', {
                params: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_DEL_WORLD_AREA, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_DEL_WORLD_AREA), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_DEL_WORLD_AREA, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 在添加、修改、删除进程配置成功后触发更新
export function* refreshAreasDeal() {
    while (true) {
        yield race({
            login: take(actionSucceeded(REQ_LOGIN)),
            add: take(actionSucceeded(REQ_ADD_WORLD_AREA)),
            del: take(actionSucceeded(REQ_DEL_WORLD_AREA)),
            chage: take(actionSucceeded(REQ_CHG_WORLD_AREA)),
            tick: take(MARK_AREA_CACHE_DIRTY),
        });

        // 触发查询
        yield put({type: REQ_QUERY_WORLD_AREA});
    }
}

export function* worldAreaMgrSaga() {
    yield all([
        takeEvery(REQ_QUERY_WORLD_AREA, queryAreaDeal),
        takeEvery(REQ_ADD_WORLD_AREA, addAreaDeal),
        takeEvery(REQ_CHG_WORLD_AREA, chgAreaDeal),
        takeEvery(REQ_DEL_WORLD_AREA, deleteAreaDeal),

        fork(refreshAreasDeal),
    ]);
}