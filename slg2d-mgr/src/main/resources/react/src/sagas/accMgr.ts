import { all, call, fork, put, race, take, takeEvery } from 'redux-saga/effects';
import { client } from '../client';
import {
    actionSucceeded,
    FailedAction,
    IReqAction,
    REQ_ADD_ACC,
    REQ_CHG_ACC,
    REQ_DEL_ACC,
    REQ_LOGIN,
    REQ_MOD_ACC_PWD,
    REQ_QUERY_ACC_MGR
} from './ibaseaction';
import { EXCEPTION_ERROR, REQ_EXCEPTION } from '../constvalue';

// 添加所有GM账号
export function* queryMgrAccountsDeal() {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/queryAllAccount');
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_QUERY_ACC_MGR, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_QUERY_ACC_MGR), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_QUERY_ACC_MGR, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 添加GM账号
export function addAccount(data: {}): IReqAction<{}> {
    return {
        type: REQ_ADD_ACC,
        payload: data,
    };
}

function* addAccountDeal(action: IReqAction<{}>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.post('/v1/createAccount', {
                data: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_ADD_ACC, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_ADD_ACC), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_ADD_ACC, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 修改账号组
export function chgAccount(data: {}): IReqAction<{}> {
    return {
        type: REQ_CHG_ACC,
        payload: data,
    };
}

function* chgAccountDeal(action: IReqAction<{}>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.post('/v1/modifyAccount', {
                data: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_CHG_ACC, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_CHG_ACC), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_CHG_ACC, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

export interface IDelAccountData {
    id: number;
}

// 删除账号组
export function deleteAccount(id: number): IReqAction<IDelAccountData> {
    return {
        type: REQ_DEL_ACC,
        payload: {
            id
        },
    };
}

function* deleteAccountDeal(action: IReqAction<IDelAccountData>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/delAccount', {
                params: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_DEL_ACC, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_DEL_ACC), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_DEL_ACC, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 删除账号组
export function modAccPwd(data: {}): IReqAction<{}> {
    return {
        type: REQ_MOD_ACC_PWD,
        payload: data,
    };
}

function* modAccPwdDeal(action: IReqAction<{}>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.post('/v1/modifyAccountPwd', {
                data: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_MOD_ACC_PWD, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_MOD_ACC_PWD), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_MOD_ACC_PWD, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 在添加、修改、删除进程配置成功后触发更新
export function* refreshAccountMgrDeal() {
    while (true) {
        yield race({
            login: take(actionSucceeded(REQ_LOGIN)),
            add: take(actionSucceeded(REQ_ADD_ACC)),
            del: take(actionSucceeded(REQ_DEL_ACC)),
            change: take(actionSucceeded(REQ_CHG_ACC)),
        });

        // 触发查询
        yield put({type: REQ_QUERY_ACC_MGR});
    }
}

export function* accountMgrSaga() {
    yield all([
        takeEvery(REQ_QUERY_ACC_MGR, queryMgrAccountsDeal),
        takeEvery(REQ_ADD_ACC, addAccountDeal),
        takeEvery(REQ_CHG_ACC, chgAccountDeal),
        takeEvery(REQ_DEL_ACC, deleteAccountDeal),
        takeEvery(REQ_MOD_ACC_PWD, modAccPwdDeal),

        fork(refreshAccountMgrDeal),
    ]);
}