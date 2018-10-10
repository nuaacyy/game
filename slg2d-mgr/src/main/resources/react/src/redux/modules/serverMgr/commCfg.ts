import {
    actionSucceeded,
    IBaseAction,
    MARK_CREATE_MODIFY_COMM_CFG,
    REQ_ADD_COMM_CFG,
    REQ_CHG_COMM_CFG,
    REQ_QUERY_COMM_CFG,
    REQ_QUERY_COMM_DETAIL
} from '../../../sagas/ibaseaction';

export interface ICommCfg {
    id: number;
    groupName: string;
    seedNodes: string;
    kafkaAddrs: string;
    dcLogTopic: string;
}

export interface ICommCfgTopic {

}

export class CommCfgState {
    commCfgs: ICommCfg[] = [];
    topics: ICommCfgTopic[] = [];
    atCreatingOrModifying: boolean;
}

const reducerMap = new Map<string, (state: CommCfgState, action: IBaseAction) => CommCfgState>();

//
export interface IMarkCreateModifyCommonCfg {
    type: string;
    atCreatingOrModifying: boolean;
}

reducerMap[MARK_CREATE_MODIFY_COMM_CFG] = (state: CommCfgState, action: IMarkCreateModifyCommonCfg) => ({
    ...state,
    atCreatingOrModifying: action.atCreatingOrModifying
});

//
export interface IQueryCommCfgSucceedAction {
    type: string;
    payload: ICommCfg[];
}

reducerMap[actionSucceeded(REQ_QUERY_COMM_CFG)] = (state: CommCfgState, action: IQueryCommCfgSucceedAction) => ({
    ...state,
    commCfgs: action.payload,
});

//
export interface IQueryCommDetailSucceedAction {
    type: string;
    payload: {
        topics: ICommCfgTopic[];
    };
}

reducerMap[actionSucceeded(REQ_QUERY_COMM_DETAIL)] =  (state: CommCfgState, action: IQueryCommDetailSucceedAction) => ({
    ...state,
    topics: action.payload.topics,
});

//
reducerMap[actionSucceeded(REQ_ADD_COMM_CFG)] = (state: CommCfgState, action: IBaseAction) => ({
    ...state,
    atCreatingOrModifying: false, // 结束创建
});

//
reducerMap[actionSucceeded(REQ_CHG_COMM_CFG)] = (state: CommCfgState, action: IBaseAction) => ({
    ...state,
    atCreatingOrModifying: false, // 结束修改
});

export default function (state: CommCfgState = new CommCfgState(), action: IBaseAction): CommCfgState {
    let reducer = reducerMap[action.type];
    if (reducer !== undefined && reducer !== null) {
        return reducer(state, action);
    } else {
        return state;
    }
}