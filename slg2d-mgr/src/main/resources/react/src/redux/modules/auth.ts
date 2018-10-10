import { actionSucceeded, IBaseAction, REQ_LOGIN } from '../../sagas/ibaseaction';

export class AuthState {
    accName: string = '';
    logining: boolean = false; // 是否正在登录
    logined: boolean = false; // 是否已经登录
    funcTitles: string[] = [''];
}

const reducerMap = new Map<string, (state: AuthState, action: IBaseAction) => AuthState>();

export interface ILoginSucceedAction {
    type: string;
    accName: string;
}

reducerMap[actionSucceeded(REQ_LOGIN)] = (state: AuthState, action: ILoginSucceedAction) => ({
    ...state,
    logining: false,
    logined: true,
    accName: action.accName,
});

export default function (state: AuthState = new AuthState(), action: IBaseAction): AuthState {
    let reducer = reducerMap[action.type];
    if (reducer !== undefined && reducer !== null) {
        return reducer(state, action);
    } else {
        return state;
    }
}
