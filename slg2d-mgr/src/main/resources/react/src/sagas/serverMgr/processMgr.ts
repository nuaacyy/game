import { all, call, fork, put, race, take, takeEvery } from 'redux-saga/effects';
import { client } from '../../client';
import {
    actionSucceeded,
    FailedAction,
    IReqAction,
    MARK_CREATE_MODIFY_PROCESS,
    REQ_ADD_PROCESS,
    REQ_CHG_PROCESS,
    REQ_DEL_PROCESS,
    REQ_LOGIN,
    REQ_QUERY_PROCESS,
    SAVE_PROCESS_FILTERS
} from '../ibaseaction';
import { IMarkCreateModifyProcess } from '../../redux/modules/serverMgr/process';
import { EXCEPTION_ERROR, REQ_EXCEPTION } from '../../constvalue';

export function markCreateOrModifyProcess(atCreatingOrModifying: boolean): IMarkCreateModifyProcess {
    return {
        type: MARK_CREATE_MODIFY_PROCESS,
        atCreatingOrModifying,
    };
}

// 保存过滤选项
export function saveProcessFilters(filters: {}): {} {
    return {
        type: SAVE_PROCESS_FILTERS,
        payload: filters,
    };
}

// 查询进程配置处理
function* queryProcessDeal() {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/queryServer');
        });
        console.log('进程...');
        console.log(result);

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_QUERY_PROCESS, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_QUERY_PROCESS), payload: result.data, rt3: result.rt});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_QUERY_PROCESS, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

export function addProcess(data: {}): IReqAction<{}> {
    return {
        type: REQ_ADD_PROCESS,
        payload: data,
    };
}

function* addProcessDeal(action: IReqAction<{}>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.post('/v1/addServer', {
                data: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_ADD_PROCESS, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_ADD_PROCESS), payload: result.data, rt3: result.rt});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_ADD_PROCESS, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

export function chgProcess(data: {}): IReqAction<{}> {
    return {
        type: REQ_CHG_PROCESS,
        payload: data,
    };
}

function* chgProcessDeal(action: IReqAction<{}>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.post('/v1/updateServer', {
                data: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_CHG_PROCESS, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_CHG_PROCESS), payload: result.data, rt2: result.rt});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_CHG_PROCESS, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

export interface IDelProcessData {
    ip: string;
    processType: number;
}

export function delProcess(ip: string, processType: number): IReqAction<IDelProcessData> {
    return {
        type: REQ_DEL_PROCESS,
        payload: {
            ip,
            processType,
        },
    };
}

function* delProcessDeal(action: IReqAction<IDelProcessData>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/delProcess', {
                params: action.payload,
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_DEL_PROCESS, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_DEL_PROCESS), payload: result.data});
        }

    } catch (e) {
        yield put(new FailedAction(REQ_DEL_PROCESS, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

// 在添加、修改、删除进程配置成功后触发更新
export function* refreshProcessesDeal() {
    while (true) {
        yield race({
            login: take(actionSucceeded(REQ_LOGIN)),
            add: take(actionSucceeded(REQ_ADD_PROCESS)),
            del: take(actionSucceeded(REQ_DEL_PROCESS)),
            chage: take(actionSucceeded(REQ_CHG_PROCESS)),
        });

        // 触发查询
        yield put({type: REQ_QUERY_PROCESS});
    }
}

export function* processMgrSaga() {
    yield all([
        takeEvery(REQ_QUERY_PROCESS, queryProcessDeal),
        takeEvery(REQ_ADD_PROCESS, addProcessDeal),
        takeEvery(REQ_CHG_PROCESS, chgProcessDeal),
        takeEvery(REQ_DEL_PROCESS, delProcessDeal),

        fork(refreshProcessesDeal),
    ]);
}