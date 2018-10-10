import {
    actionSucceeded,
    IBaseAction,
    MARK_CREATE_MODIFY_PLATFORM,
    REQ_ADD_PLT,
    REQ_CHG_PLT,
    REQ_QUERY_PLT
} from '../../../sagas/ibaseaction';

export interface IPlatform {
    id: number;
    platformName: string;
    opId: number;
    gameId: number;
}

export class PlatformState {
    platforms: IPlatform[] = [];
    atCreatingOrModifying: boolean;
}

const reducerMap = new Map<string, (state: PlatformState, action: IBaseAction) => PlatformState>();

//
export interface IMarkCreateModifyPlatform {
    type: string;
    atCreatingOrModifying: boolean;
}

reducerMap[MARK_CREATE_MODIFY_PLATFORM] = (state: PlatformState, action: IMarkCreateModifyPlatform) => ({
    ...state,
    atCreatingOrModifying: action.atCreatingOrModifying,
});

//
export interface IQueryPlySucceedAction {
    type: string;
    payload: IPlatform[];
}

reducerMap[actionSucceeded(REQ_QUERY_PLT)] = (state: PlatformState, action: IQueryPlySucceedAction) => ({
    ...state,
    platforms: action.payload,
});

//
reducerMap[actionSucceeded(REQ_ADD_PLT)] = (state: PlatformState, action: IBaseAction) => ({
    ...state,
    atCreatingOrModifying: false, // 结束创建
});

//
reducerMap[actionSucceeded(REQ_CHG_PLT)] = (state: PlatformState, action: IBaseAction) => ({
    ...state,
    atCreatingOrModifying: false, // 结束修改
});

export default function (state: PlatformState = new PlatformState(), action: IBaseAction): PlatformState {
    let reducer = reducerMap[action.type];
    if (reducer !== undefined && reducer !== null) {
        return reducer(state, action);
    } else {
        return state;
    }
}
