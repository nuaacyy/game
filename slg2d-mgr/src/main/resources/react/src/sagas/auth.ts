import { all, call, put, takeEvery } from 'redux-saga/effects';
import { message } from 'antd';
import { client } from '../client';
import { actionSucceeded, FailedAction, IReqAction, REQ_LOGIN } from './ibaseaction';
import { EXCEPTION_ERROR, REQ_EXCEPTION } from '../constvalue';

export interface ILoginData {
    name: string;
    pwd: string;
}

export function login(name: string, pwd: string): IReqAction<ILoginData> {
    return {
        type: REQ_LOGIN,
        payload: {
            name,
            pwd,
        },
    };
}

function* loginDeal(action: IReqAction<ILoginData>) {
    try {
        // 发送请求查询
        const result = yield call(() => {
            return client.get('/v1/login', {
                params: {
                    name: action.payload.name,
                    pwd: action.payload.pwd,
                },
            });
        });

        if (result.rt !== 1) {
            yield put(new FailedAction(REQ_LOGIN, result.rt, result.errorMsg));
        } else {
            yield put({type: actionSucceeded(REQ_LOGIN), accName: action.payload.name});
        }

    } catch (e) {
        console.error(e);
        yield put(new FailedAction(REQ_LOGIN, EXCEPTION_ERROR, REQ_EXCEPTION));
    }
}

interface IEveryRtAction {
    type: string;
    rt: number;
    errorMsg: string | null;
}

function rtDeal(action: IEveryRtAction) {
    const actionType = action.type;
    if (!(actionType == null || actionType === undefined) && actionType.indexOf('_FAILED') !== -1) {
        message.error('返回错误：' + action.errorMsg);
    }
}

export function* authSaga() {
    yield all([
        takeEvery(REQ_LOGIN, loginDeal),
        takeEvery('*', rtDeal),
    ]);
}