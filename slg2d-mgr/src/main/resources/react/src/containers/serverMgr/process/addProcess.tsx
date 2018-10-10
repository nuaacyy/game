import * as React from 'react';
import { connect } from 'react-redux';

import { bindActionCreators, Dispatch } from 'redux';
import { History } from 'history';

import { AddOrChgProcessCmp } from './addOrChgProcessCmp';
import { addProcess, markCreateOrModifyProcess } from '../../../sagas/serverMgr/processMgr';
import { ICombineState } from '../../../redux/reducer';
import { WORLD } from './process';
import { GAME_SERVER } from '../../../const/funcType';
import { IMarkCreateModifyProcess, IProcess } from '../../../redux/modules/serverMgr/process';
import { IPlatform } from '../../../redux/modules/serverMgr/platform';
import { ICommCfg } from '../../../redux/modules/serverMgr/commCfg';
import { IReqAction } from '../../../sagas/ibaseaction';

interface IStateProps {
    history: History;
    servers: IProcess[];
    commCfgs: ICommCfg[];
    platforms: IPlatform[];
    atCreatingOrModifying: boolean;
}

interface IDispatchProps {
    markCreateOrModifyProcess(atCreatingOrModifying: boolean): IMarkCreateModifyProcess;

    addProcess(data: {}): IReqAction<{}>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        servers: state.serverMgr.process.servers,
        platforms: state.serverMgr.platform.platforms,
        commCfgs: state.serverMgr.commCfg.commCfgs,
        atCreatingOrModifying: state.serverMgr.process.atCreatingOrModifying,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        markCreateOrModifyProcess,
        addProcess,
    }, dispatch);
};

interface IState {
    targetServer: IProcess;
}

class AddProcess extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        let platId = 1;
        if (this.props.platforms[0] !== undefined) {
            platId = this.props.platforms[0].id;
        }

        // 这里取查询设备返回的推荐kafkaPid值
        let recommandKafkaPId = 0;
        const servers = this.props.servers;
        servers.map((eachProcess: IProcess) => {
            // 设置推荐值
            if (eachProcess.recommandKafkaPId !== 0) {
                recommandKafkaPId = eachProcess.recommandKafkaPId;
            }
        });

        const targetServer: IProcess = {
            id: 0,
            platId,
            httpPort: 4444,
            tcpPort: 4454,
            seedPort: 4464,
            processIp: '',
            processNum: 10,
            processName: '',
            processType: WORLD,
            processTcpAddr: '',
            processWebAddr: '',
            kafkaPId: recommandKafkaPId,
            clusterId: 0,
            seedNode: 0,
            recommandKafkaPId: 0,
        };
        this.state = {
            targetServer,
        };

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
            addOrChgProcess: this.props.addProcess,
            cancelSubmit: this.cancelSubmit,
            chgMark: false,
        };

        return (
            <AddOrChgProcessCmp {...cmpProps} />
        );
    }

}

export default connect(mapStateToProps, mapDispatchToProps)(AddProcess);