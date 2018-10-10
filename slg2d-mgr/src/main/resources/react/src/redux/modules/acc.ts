import { actionSucceeded, IBaseAction, REQ_QUERY_ACC_MGR } from '../../sagas/ibaseaction';

export interface IMgrAccount {
    id: number;
    name: string;
    phone: string;
    accName: string;
    groupId: number;
    accstate: number;
}

export class AccState {
    chgAccRt: number = -1;
    mgraccounts: IMgrAccount[] = [];
}

const reducerMap = new Map<string, (state: AccState, action: IBaseAction) => AccState>();

export interface IQueryAccMgrSucceedAction {
    type: string;
    payload: IMgrAccount[];
}

reducerMap[actionSucceeded(REQ_QUERY_ACC_MGR)] = (state: AccState, action: IQueryAccMgrSucceedAction) => ({
    ...state,
    mgraccounts: action.payload,
});

export default function (state: AccState = new AccState(), action: IBaseAction): AccState {
    let reducer = reducerMap[action.type];
    if (reducer !== undefined && reducer !== null) {
        return reducer(state, action);
    } else {
        return state;
    }
}