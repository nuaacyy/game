import { combineReducers } from 'redux';
import auth, { AuthState } from './modules/auth';
import acc, { AccState } from './modules/acc';
import accGroup, { AccGroupState } from './modules/accGroup';
import areaWorld, { AreaWorldState } from './modules/serverMgr/areaWorld';
import main, { MainState } from './modules/main';
import platform, { PlatformState } from './modules/serverMgr/platform';
import dataSource, { DataSourceState } from './modules/serverMgr/dataSource';
import process, { ProcessState } from './modules/serverMgr/process';
import selectArea, { SelectAreaState } from './modules/selectArea';
import commCfg, { CommCfgState } from './modules/serverMgr/commCfg';

// 全局state类型
// 和redux连接的组件的props来自这个state。
export interface ICombineState {
    main: MainState;
    auth: AuthState;
    acc: AccState;
    accGroup: AccGroupState;
    serverMgr: {
        process: ProcessState;
        platform: PlatformState;
        commCfg: CommCfgState;
        areaWorld: AreaWorldState;
        dataSource: DataSourceState;
    };
    selectArea: SelectAreaState;
}

// 组合reducer

export default combineReducers({
    main,
    auth,
    acc,
    accGroup,
    serverMgr: combineReducers({
        process,
        platform,
        commCfg,
        areaWorld,
        dataSource,
    }),
    selectArea,
});