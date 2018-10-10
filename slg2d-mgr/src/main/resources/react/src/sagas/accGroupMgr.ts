import { all, call, fork, put, race, take, takeEvery } from 'redux-saga/effects';
import { client } from '../client';
import {
    actionSucceeded,
    FailedAction,
    IReqAction,
    REQ_ADD_ACCGROUP,
    REQ_CHG_ACCGROUP,
    REQ_DEL_ACCGROUP,
    REQ_LOGIN,
    REQ_QUERY_ACCGROUP_MGR,
    REQ_QUERY_ACCRIGHTS,
} from './ibaseaction';
import { EXCEPTION_ERROR, REQ_EXCEPTION } from '../constvalue';

// 查询账号组
export function* queryAllGroupsDeal() {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/queryAllGroups');
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_QUERY_ACCGROUP_MGR, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_QUERY_ACCGROUP_MGR), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_QUERY_ACCGROUP_MGR, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 查询所有权限
export function* queryAllRightsDeal() {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/queryAllAuthority');
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_QUERY_ACCRIGHTS, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_QUERY_ACCRIGHTS), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_QUERY_ACCRIGHTS, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 添加账号组
export function addAccGroup(data: {}): IReqAction<{}> {
    return {
        type: REQ_ADD_ACCGROUP,
        payload: data,
    };
}

function* addAccGroupDeal(action: IReqAction<{}>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.post('/v1/createGroup', {
                data: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_ADD_ACCGROUP, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_ADD_ACCGROUP), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_ADD_ACCGROUP, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 修改账号组
export function chgAccGroup(data: {}): IReqAction<{}> {
    return {
        type: REQ_CHG_ACCGROUP,
        payload: data,
    };
}

function* chgAccGroupDeal(action: IReqAction<{}>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.post('/v1/modifyGroup', {
                data: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_CHG_ACCGROUP, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_CHG_ACCGROUP), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_CHG_ACCGROUP, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

export interface IDelAccGroupData {
    id: number;
}

// 删除账号组
export function deleteAccGroup(id: number): IReqAction<IDelAccGroupData> {
    return {
        type: REQ_DEL_ACCGROUP,
        payload: {
            id,
        },
    };
}

function* deleteAccGroupDeal(action: IReqAction<IDelAccGroupData>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/delGroup', {
                params: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_DEL_ACCGROUP, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_DEL_ACCGROUP), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_DEL_ACCGROUP, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 在添加、修改、删除进程配置成功后触发更新
export function* refreshAccGroupDeal() {
    while (true) {
        yield race({
            login: take(actionSucceeded(REQ_LOGIN)),
            add: take(actionSucceeded(REQ_ADD_ACCGROUP)),
            del: take(actionSucceeded(REQ_DEL_ACCGROUP)),
            change: take(actionSucceeded(REQ_CHG_ACCGROUP)),
        });

        // 触发查询
        yield put({type: REQ_QUERY_ACCGROUP_MGR});
        yield put({type: REQ_QUERY_ACCRIGHTS});
    }
}

export function* accGroupMgrSaga() {
    yield all([
        takeEvery(REQ_QUERY_ACCGROUP_MGR, queryAllGroupsDeal),
        takeEvery(REQ_QUERY_ACCRIGHTS, queryAllRightsDeal),
        takeEvery(REQ_ADD_ACCGROUP, addAccGroupDeal),
        takeEvery(REQ_CHG_ACCGROUP, chgAccGroupDeal),
        takeEvery(REQ_DEL_ACCGROUP, deleteAccGroupDeal),

        fork(refreshAccGroupDeal),
    ]);
}