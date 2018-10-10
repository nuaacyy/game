import { all, call, fork, put, race, take, takeEvery } from 'redux-saga/effects';
import { client } from '../../client';
import {
    actionSucceeded,
    FailedAction,
    IReqAction,
    MARK_CREATE_MODIFY_DATA_SOURCE,
    REQ_ADD_DATA_SOURCE,
    REQ_CHG_DATA_SOURCE,
    REQ_DEL_DATA_SOURCE,
    REQ_LOGIN,
    REQ_QUERY_DATA_SOURCE
} from '../ibaseaction';
import { IMarkCreateModifyDataSource } from '../../redux/modules/serverMgr/dataSource';
import { EXCEPTION_ERROR, REQ_EXCEPTION } from '../../constvalue';

export function markCreateOrModifyDataSource(atCreatingOrModifying: boolean): IMarkCreateModifyDataSource {
    return {
        type: MARK_CREATE_MODIFY_DATA_SOURCE,
        atCreatingOrModifying,
    };
}

// 查询数据
export function* queryDataSourceDeal() {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/queryDatabaseConfig');
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_QUERY_DATA_SOURCE, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_QUERY_DATA_SOURCE), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_QUERY_DATA_SOURCE, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 添加数据
export function addDataSource(data: {}): IReqAction<{}> {
    return {
        type: REQ_ADD_DATA_SOURCE,
        payload: data,
    };
}

function* addDataSourceDeal(action: IReqAction<{}>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.post('/v1/addDatabaseConfig', {
                data: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_ADD_DATA_SOURCE, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_ADD_DATA_SOURCE), payload: result.data, rt3: result.rt});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_ADD_DATA_SOURCE, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 修改数据
export function chgDataSource(data: {}): IReqAction<{}> {
    return {
        type: REQ_CHG_DATA_SOURCE,
        payload: data,
    };
}

export function* changeDataSourceDeal(action: IReqAction<{}>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.post('/v1/updateDatabaseConfig', {
                data: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_CHG_DATA_SOURCE, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_CHG_DATA_SOURCE)});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_CHG_DATA_SOURCE, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

export interface IDelDataSource {
    clusterId: number;
    shardId: number;
}

// 删除数据
export function deleteDataSource(clusterId: number, shardId: number): IReqAction<IDelDataSource> {
    return {
        type: REQ_DEL_DATA_SOURCE,
        payload: {
            clusterId,
            shardId,
        },
    };
}

export function* deleteDataSourceDeal(action: IReqAction<IDelDataSource>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/delDatabaseConfig', {
                params: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_DEL_DATA_SOURCE, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_DEL_DATA_SOURCE)});
        }
    } catch (e) {
        yield put(new FailedAction(REQ_DEL_DATA_SOURCE, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 在添加、修改、数据库配置成功后触发更新
export function* refreshDataSourceDeal() {
    while (true) {
        yield race({
            login: take(actionSucceeded(REQ_LOGIN)),
            add: take(actionSucceeded(REQ_ADD_DATA_SOURCE)),
            chage: take(actionSucceeded(REQ_CHG_DATA_SOURCE)),
            del: take(actionSucceeded(REQ_DEL_DATA_SOURCE)),
        });

        // 触发查询
        yield put({type: REQ_QUERY_DATA_SOURCE});
    }
}

export function* dataSourceMgrSaga() {
    yield all([
        takeEvery(REQ_QUERY_DATA_SOURCE, queryDataSourceDeal),
        takeEvery(REQ_ADD_DATA_SOURCE, addDataSourceDeal),
        takeEvery(REQ_CHG_DATA_SOURCE, changeDataSourceDeal),
        takeEvery(REQ_DEL_DATA_SOURCE, deleteDataSourceDeal),

        fork(refreshDataSourceDeal),
    ]);
}