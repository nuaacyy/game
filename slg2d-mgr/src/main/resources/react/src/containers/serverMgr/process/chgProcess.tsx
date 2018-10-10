import * as React from 'react';
import { connect } from 'react-redux';
import { match } from 'react-router';
import { bindActionCreators, Dispatch } from 'redux';
import { History } from 'history';

import { AddOrChgProcessCmp } from './addOrChgProcessCmp';
import { chgProcess, markCreateOrModifyProcess } from '../../../sagas/serverMgr/processMgr';
import { ICombineState } from '../../../redux/reducer';
import { GAME_SERVER } from '../../../const/funcType';
import { IMarkCreateModifyProcess, IProcess } from '../../../redux/modules/serverMgr/process';
import { IPlatform } from '../../../redux/modules/serverMgr/platform';
import { ICommCfg } from '../../../redux/modules/serverMgr/commCfg';
import { WORLD } from './process';
import { IReqAction } from '../../../sagas/ibaseaction';

interface IChgProcessParam {
    sid: string;
}

interface IStateProps {
    history: History;
    match: match<IChgProcessParam>;
    servers: IProcess[];
    commCfgs: ICommCfg[];
    platforms: IPlatform[];
    atCreatingOrModifying: boolean;
}

interface IDispatchProps {
    markCreateOrModifyProcess(atCreatingOrModifying: boolean): IMarkCreateModifyProcess;

    chgProcess(data: {}): IReqAction<{}>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        servers: state.serverMgr.process.servers,
        commCfgs: state.serverMgr.commCfg.commCfgs,
        platforms: state.serverMgr.platform.platforms,
        atCreatingOrModifying: state.serverMgr.process.atCreatingOrModifying,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        markCreateOrModifyProcess,
        chgProcess,
    }, dispatch);
};

interface IState {
    targetServer: IProcess;
}

class ChgProcess extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        const targetServerId = parseInt(this.props.match.params.sid, 10);
        let targetServer = null;
        const servers = this.props.servers;
        for (const server of servers) {
            if (server.id === targetServerId) {
                targetServer = server;
            }
        }

        if (!(targetServer === null || targetServer === undefined)) {
            this.state = {
                targetServer,
            };
        } else {
            targetServer = {
                id: 0,
                platId: 0,
                httpPort: 4444,
                tcpPort: 4454,
                seedPort: 4464,
                processIp: '',
                processNum: 10,
                processName: '',
                processType: WORLD,
                processTcpAddr: '',
                processWebAddr: '',
                kafkaPId: 0,
                clusterId: 0,
                seedNode: 0,
                recommandKafkaPId: 0,
            };
            this.state = {targetServer};
        }

        this.props.markCreateOrModifyProcess(true);
    }

    componentWillReceiveProps(nextProps: IAppProps) {
        if (!nextProps.atCreatingOrModifying && this.props.atCreatingOrModifying) {
            this.props.history.push(GAME_SERVER);
        }
    }

    cancelSubmit = () => {
        this.props.markCreateOrModifyProcess(false);
    };

    render() {
        const cmpProps = {
            servers: this.props.servers,
            commCfgs: this.props.commCfgs,
            platforms: this.props.platforms,
            targetServer: this.state.targetServer,
            addOrChgProcess: this.props.chgProcess,
            cancelSubmit: this.cancelSubmit,
            chgMark: true,
        };

        return (
            <AddOrChgProcessCmp {...cmpProps} />
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(ChgProcess);
