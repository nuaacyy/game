import { GAME_MGR } from '../../const/funcType';
import { IBaseAction, SHOW_SPEC_FUNC } from '../../sagas/ibaseaction';

// 显示特定功能的面板
export function showSpecFuncs(funcType: string): {} {
    return {
        type: SHOW_SPEC_FUNC,
        funcType,
    };
}

export class MainState {
    funcType: string = GAME_MGR;
}

const reducerMap = new Map<string, (state: MainState, action: IBaseAction) => MainState>();

export interface IShowSpecFuncAction {
    type: string;
    funcType: string;
}

reducerMap[SHOW_SPEC_FUNC] = (state: MainState, action: IShowSpecFuncAction) => ({
    ...state,
    funcType: action.funcType,
});

export default function (state: MainState = new MainState(), action: IBaseAction): MainState {
    let reducer = reducerMap[action.type];
    if (reducer !== undefined && reducer !== null) {
        return reducer(state, action);
    } else {
        return state;
    }
}
