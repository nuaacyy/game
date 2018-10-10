import { all, fork } from 'redux-saga/effects';
import * as platformMgr from './serverMgr/platformMgr';
import * as processMgr from './serverMgr/processMgr';
import * as worldAreaMgr from './serverMgr/areaWorldMgr';
import * as accountMgr from './accMgr';
import * as accGroupMgr from './accGroupMgr';

import * as dataSourceMgr from './serverMgr/dataSourceMgr';

import * as auth from './auth';
import * as commCfgMgr from './serverMgr/commCfgMgr';

export function* rootSaga(): Iterator<{}> {
    yield all([
        fork(accountMgr.accountMgrSaga),
        fork(accGroupMgr.accGroupMgrSaga),
        fork(auth.authSaga),
        fork(commCfgMgr.commCfgMgrSaga),
        fork(worldAreaMgr.worldAreaMgrSaga),
        fork(platformMgr.platformMgrSaga),
        fork(processMgr.processMgrSaga),

        // 数据库配置
        fork(dataSourceMgr.dataSourceMgrSaga),
    ]);
}