import * as React from 'react';
import { Route, RouteComponentProps } from 'react-router';
import { Card, Layout } from 'antd';
import FuncTree from './functree/functree';
import MainHeader from './header/header';

import MainPanel from './gameMgr/mainPanel/mainpanel';

import AccMgr from './gmMgr/accMgr/accmgr';
import AddAccount from './gmMgr/accMgr/addacc';
import ChgAccount from './gmMgr/accMgr/chgacc';
import AccGroupMgr from './gmMgr/accGroupMgr/accgroupmgr';
import AddAccountGroup from './gmMgr/accGroupMgr/addaccgroup';
import ChgAccountGroup from './gmMgr/accGroupMgr/chgaccgroup';
import ChgAccPwd from './gmMgr/accMgr/chgaccpwd';

import PlatformMgr from './serverMgr/platform/platform';
import AddPlatform from './serverMgr/platform/addplatform';
import ChgPlatform from './serverMgr/platform/chgplatform';
import GameServerMgr from './serverMgr/process/process';
import AddProcess from './serverMgr/process/addProcess';
import ChgProcess from './serverMgr/process/chgProcess';

import GameAreaMgr from './serverMgr/areaWorld/worldarea';
import AddArea from './serverMgr/areaWorld/addworldarea';
import ChgGameArea from './serverMgr/areaWorld/chgworldarea';

import CommCfgMgr from './serverMgr/commCfg/commCfgMgr';
import AddCommCfg from './serverMgr/commCfg/addCommCfg';
import ChgCommCfg from './serverMgr/commCfg/chgCommCfg';
import CommDetail from './serverMgr/commCfg/commDetail';

import DataSourceMgr from './serverMgr/dataSource/datasource';
import AddDataSource from './serverMgr/dataSource/adddatasource';
import ChgDataSource from './serverMgr/dataSource/chgdatasource';

const {Header, Sider, Content} = Layout;

const Panels = ({match}: RouteComponentProps<{}>) => {
    return (
        <Layout style={{height: '100vh'}}>
            <Sider className="gmsider">
                <div className="gmlogo">
                    <h1>征服后台</h1>
                </div>

                <Route component={FuncTree}/>
            </Sider>

            <Layout>
                <Header className="gmheader">
                    <Route component={MainHeader}/>
                </Header>

                <div>
                    <Content className="gmcontent">
                        <Card bordered={false}>
                            {/*研发工具*/}

                            {/*游戏管理*/}
                            <Route path={`${match.path}/mainpanel`} component={MainPanel}/>

                            {/*GM管理*/}
                            <Route path={`${match.path}/accmgr`} component={AccMgr}/>
                            <Route path={`${match.path}/addacc`} component={AddAccount}/>
                            <Route path={`${match.path}/chgacc/:accId`} component={ChgAccount}/>
                            <Route path={`${match.path}/chgaccpwd`} component={ChgAccPwd}/>

                            <Route path={`${match.path}/accgroupmgr`} component={AccGroupMgr}/>
                            <Route path={`${match.path}/addaccgroup`} component={AddAccountGroup}/>
                            <Route path={`${match.path}/chgaccgroup/:gId`} component={ChgAccountGroup}/>

                            {/*服务器管理*/}

                            <Route path={`${match.path}/gamemgr`} component={GameAreaMgr}/>
                            <Route path={`${match.path}/addarea`} component={AddArea}/>
                            <Route path={`${match.path}/chgarea/:aid`} component={ChgGameArea}/>

                            <Route path={`${match.path}/commCfgMgr`} component={CommCfgMgr}/>
                            <Route path={`${match.path}/commDetail`} component={CommDetail}/>
                            <Route path={`${match.path}/addCommCfg`} component={AddCommCfg}/>
                            <Route path={`${match.path}/chgCommCfg/:commCfgId`} component={ChgCommCfg}/>

                            <Route path={`${match.path}/platform`} component={PlatformMgr}/>
                            <Route path={`${match.path}/addplatform`} component={AddPlatform}/>
                            <Route path={`${match.path}/chgplatform/:pltid`} component={ChgPlatform}/>

                            <Route path={`${match.path}/gameserver`} component={GameServerMgr}/>
                            <Route path={`${match.path}/addserver`} component={AddProcess}/>
                            <Route path={`${match.path}/chgserver/:sid`} component={ChgProcess}/>

                            <Route path={`${match.path}/datasource`} component={DataSourceMgr}/>
                            <Route path={`${match.path}/adddatasource`} component={AddDataSource}/>
                            <Route path={`${match.path}/chgdatasource/:configName`} component={ChgDataSource}/>

                            {/*服务器监控*/}

                        </Card>
                    </Content>
                </div>
            </Layout>
        </Layout>
    );
};

export default Panels;