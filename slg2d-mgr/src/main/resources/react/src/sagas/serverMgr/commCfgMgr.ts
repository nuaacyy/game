import { all, call, fork, put, race, take, takeEvery } from 'redux-saga/effects';
import { client } from '../../client';
import {
    actionSucceeded,
    FailedAction,
    IReqAction,
    MARK_CREATE_MODIFY_COMM_CFG,
    REQ_ADD_COMM_CFG,
    REQ_CHG_COMM_CFG,
    REQ_DEL_COMM_CFG,
    REQ_LOGIN,
    REQ_QUERY_COMM_CFG,
    REQ_QUERY_COMM_DETAIL
} from '../ibaseaction';
import { IMarkCreateModifyCommonCfg } from '../../redux/modules/serverMgr/commCfg';
import { EXCEPTION_ERROR, REQ_EXCEPTION } from '../../constvalue';

export function markCreateOrModifyCommonCfg(atCreatingOrModifying: boolean): IMarkCreateModifyCommonCfg {
    return {
        type: MARK_CREATE_MODIFY_COMM_CFG,
        atCreatingOrModifying,
    };
}

function* queryAllCommCfgsDeal() {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/queryAllCommCfgs');
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_QUERY_COMM_CFG, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_QUERY_COMM_CFG), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_QUERY_COMM_CFG, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

export interface QueryCommCfgDetailData {
    id: number;
}

// 查询集群配置详细信息
export function queryCommCfgDetail(commCfgId: number): IReqAction<QueryCommCfgDetailData> {
    return {
        type: REQ_QUERY_COMM_DETAIL,
        payload: {
            id: commCfgId,
        },
    };
}

export function* queryCommCfgDetailDeal(action: IReqAction<QueryCommCfgDetailData>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/queryCommCfgDetail', {
                params: action.payload,
            });
        });
        console.log('集群信息。。');
        console.log(result);

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_QUERY_COMM_DETAIL, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_QUERY_COMM_DETAIL), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_QUERY_COMM_DETAIL, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 添加账号组
export function addCommCfg(data: {}): IReqAction<{}> {
    return {
        type: REQ_ADD_COMM_CFG,
        payload: data,
    };
}

function* addCommCfgDeal(action: IReqAction<{}>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.post('/v1/addCommCfg', {
                data: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_ADD_COMM_CFG, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_ADD_COMM_CFG), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_ADD_COMM_CFG, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 修改账号组
export function chgCommCfg(data: {}): IReqAction<{}> {
    return {
        type: REQ_CHG_COMM_CFG,
        payload: data,
    };
}

function* chgCommCfgDeal(action: IReqAction<{}>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.post('/v1/chgCommCfg', {
                data: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_CHG_COMM_CFG, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_CHG_COMM_CFG), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_CHG_COMM_CFG, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

export interface IDelCommCfgData {
    id: number;
}

// 删除账号组
export function delCommCfg(id: number): IReqAction<IDelCommCfgData> {
    return {
        type: REQ_DEL_COMM_CFG,
        payload: {
            id,
        },
    };
}

function* delCommCfgDeal(action: IReqAction<IDelCommCfgData>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/delCommCfg', {
                params: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_DEL_COMM_CFG, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_DEL_COMM_CFG), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_DEL_COMM_CFG, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 在添加、修改、删除进程配置成功后触发更新
export function* refreshCommCfgDeal() {
    while (true) {
        yield race({
            login: take(actionSucceeded(REQ_LOGIN)),
            add: take(actionSucceeded(REQ_ADD_COMM_CFG)),
            del: take(actionSucceeded(REQ_DEL_COMM_CFG)),
            change: take(actionSucceeded(REQ_CHG_COMM_CFG)),
        });

        // 触发查询
        yield put({type: REQ_QUERY_COMM_CFG});
    }
}

export function* commCfgMgrSaga() {
    yield all([
        takeEvery(REQ_QUERY_COMM_CFG, queryAllCommCfgsDeal),
        takeEvery(REQ_ADD_COMM_CFG, addCommCfgDeal),
        takeEvery(REQ_CHG_COMM_CFG, chgCommCfgDeal),
        takeEvery(REQ_DEL_COMM_CFG, delCommCfgDeal),
        takeEvery(REQ_QUERY_COMM_DETAIL, queryCommCfgDetailDeal),

        fork(refreshCommCfgDeal),
    ]);
}