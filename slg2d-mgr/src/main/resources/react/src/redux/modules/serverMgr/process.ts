import {
    actionSucceeded,
    IBaseAction,
    MARK_CREATE_MODIFY_PROCESS,
    REQ_ADD_PROCESS,
    REQ_CHG_PROCESS,
    REQ_QUERY_PROCESS,
    SAVE_PROCESS_FILTERS
} from '../../../sagas/ibaseaction';

export interface IProcess {
    id: number;
    platId: number;
    tcpPort: number;
    httpPort: number;
    seedPort: number;
    processIp: string;
    processNum: number;
    processName: string;
    processType: number;
    processTcpAddr: string;
    processWebAddr: string;
    kafkaPId: number;
    clusterId: number;
    seedNode: number;
    recommandKafkaPId: number;
}

export class ProcessState {
    servers: IProcess[] = [];

    filter4Cluster: string[] = [];
    filter4ProcessType: string[] = [];

    atCreatingOrModifying: boolean;
}

const reducerMap = new Map<string, (state: ProcessState, action: IBaseAction) => ProcessState>();

//
export interface IMarkCreateModifyProcess {
    type: string;
    atCreatingOrModifying: boolean;
}

reducerMap[MARK_CREATE_MODIFY_PROCESS] = (state: ProcessState, action: IMarkCreateModifyProcess) => ({
    ...state,
    atCreatingOrModifying: action.atCreatingOrModifying,
});

//
export interface ISaveProcessFiltersAction {
    type: string;
    payload: {
        clusterId: string[];
        processType: string[];
    };
}

reducerMap[SAVE_PROCESS_FILTERS] = (state: ProcessState, action: ISaveProcessFiltersAction) => {
    const payload = action.payload;
    return {
        ...state,
        filter4Cluster: payload.clusterId,
        filter4ProcessType: payload.processType,
    };
};

//
export interface IQueryProcessSucceedAction {
    type: string;
    payload: IProcess[];
}

reducerMap[actionSucceeded(REQ_QUERY_PROCESS)] = (state: ProcessState, action: IQueryProcessSucceedAction) => ({
    ...state,
    servers: action.payload,
});

//
reducerMap[actionSucceeded(REQ_ADD_PROCESS)] = (state: ProcessState, action: IBaseAction) => ({
    ...state,
    atCreatingOrModifying: false, // 结束创建
});

//
reducerMap[actionSucceeded(REQ_CHG_PROCESS)] = (state: ProcessState, action: IBaseAction) => ({
    ...state,
    atCreatingOrModifying: false, // 结束修改
});

export default function (state: ProcessState = new ProcessState(), action: IBaseAction): ProcessState {
    let reducer = reducerMap[action.type];
    if (reducer !== undefined && reducer !== null) {
        return reducer(state, action);
    } else {
        return state;
    }
}
