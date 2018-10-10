import { bindActionCreators, Dispatch } from 'redux';
import * as React from 'react';
import { Button, Col, Modal, Row, Table } from 'antd';
import { connect } from 'react-redux';
import {
    delCommCfg,
    IDelCommCfgData,
    queryCommCfgDetail,
    QueryCommCfgDetailData
} from '../../../sagas/serverMgr/commCfgMgr';

import { History } from 'history';
import { ICombineState } from '../../../redux/reducer';
import { ADD_COMM_CFG, CHG_COMM_CFG } from '../../../const/funcType';
import { ICommCfg } from '../../../redux/modules/serverMgr/commCfg';
import { IReqAction } from '../../../sagas/ibaseaction';

const confirm = Modal.confirm;

interface IStateProps {
    history: History;
    commCfgs: ICommCfg[];
}

interface IDispatchProps {
    queryCommCfgDetail(commCfgId: number): IReqAction<QueryCommCfgDetailData>;

    delCommCfg(id: number): IReqAction<IDelCommCfgData>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        commCfgs: state.serverMgr.commCfg.commCfgs,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        queryCommCfgDetail,
        delCommCfg,
    }, dispatch);
};

interface IState {
    selectId: number;
}

class CommCfgMgr extends React.Component<IAppProps, IState> {

    columns = [
        {title: 'ID', dataIndex: 'id', key: 'id'},
        {title: '集群名', dataIndex: 'groupName', key: 'groupName'},
        {title: 'Kafka节点', dataIndex: 'kafkaAddrs', key: 'kafkaAddrs'},
        {title: '日志Topic', dataIndex: 'dcLogTopic', key: 'dcLogTopic'},
        {
            title: '状态', key: 'status',
            render: (text: string, record: ICommCfg) => (
                <div>
                    <Button type="primary" onClick={() => {
                        this.queryCommDetail(record.id);
                    }}>状态</Button>
                </div>
            ),
        },
        {
            title: '操作', key: 'action',
            render: (text: string, record: ICommCfg) => (
                <div>
                    <Button type="primary" onClick={() => {
                        this.chgCommCfg(record.id);
                    }}>修改</Button>
                    <Button type="danger" onClick={() => {
                        this.delConfirm(record.id);
                    }}>删除</Button>
                </div>
            ),
        },
    ];

    constructor(props: IAppProps) {
        super(props);

        this.state = {
            selectId: 0,
        };
    }

    queryCommDetail = (recordId: number) => {
        this.props.queryCommCfgDetail(recordId);
    };

    delConfirm = (recordId: number) => {
        confirm({
            title: '删除集群配置',
            content: '你确定要删除这条记录？',
            onOk: () => {
                this.props.delCommCfg(recordId);
            },
        });
    };

    addCommCfg = () => {
        this.props.history.push(ADD_COMM_CFG);
    };

    chgCommCfg = (recordId: number) => {
        this.props.history.push(CHG_COMM_CFG + '/' + recordId);
    };

    render() {
        const commCfgs = this.props.commCfgs;

        return (
            <div>
                <Row>
                    <Col span={24}>
                        <Button type="primary" onClick={this.addCommCfg}>添加新组</Button>
                    </Col>
                </Row>

                <Row>
                    <Col span={24}>
                        <Table rowKey="id" columns={this.columns} dataSource={commCfgs}
                               size="small"/>
                    </Col>
                </Row>
            </div>
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(CommCfgMgr);