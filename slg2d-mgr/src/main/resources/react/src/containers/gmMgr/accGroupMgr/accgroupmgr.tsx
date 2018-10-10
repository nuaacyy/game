import * as React from 'react';
import { connect } from 'react-redux';

import { bindActionCreators, Dispatch } from 'redux';
import { Button, Col, Modal, Row, Table } from 'antd';
import { deleteAccGroup, IDelAccGroupData } from '../../../sagas/accGroupMgr';
import { History } from 'history';
import { ICombineState } from '../../../redux/reducer';
import { ADD_ACC_GROUP, CHG_ACC_GROUP } from '../../../const/funcType';
import { IMgrAccGroup } from '../../../redux/modules/accGroup';
import { IReqAction } from '../../../sagas/ibaseaction';

const confirm = Modal.confirm;

interface IStateProps {
    history: History;
    mgraccgroups: IMgrAccGroup[];
}

interface IDispatchProps {
    deleteAccGroup(id: number): IReqAction<IDelAccGroupData>;
}

type IAppProps = IStateProps & IDispatchProps;

const mapStateToProps = (state: ICombineState) => {
    return {
        mgraccgroups: state.accGroup.mgraccgroups,
    };
};

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        deleteAccGroup,
    }, dispatch);
};

interface IState {
}

class AccGroupMgr extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        this.state = {};
    }

    chgAccGroup = (recordId: number) => {
        this.props.history.push(CHG_ACC_GROUP + '/' + recordId);
    };

    addAccGroup = () => {
        this.props.history.push(ADD_ACC_GROUP);
    };

    delConfirm = (recordId: number) => {
        confirm({
            title: '删除账号组配置',
            content: '你确定要删除这条记录？',
            onOk: () => {
                this.props.deleteAccGroup(recordId);
            },
        });
    };

    render() {
        const mgraccgroups = this.props.mgraccgroups;

        const columns = [
            {title: '选择平台', dataIndex: 'id', key: 'id'},
            {title: '组名', dataIndex: 'groupName', key: 'groupName'},
            {title: 'QA', dataIndex: 'isQA', key: 'isQA'},
            {title: '状态', dataIndex: 'state', key: 'state'},
            {
                title: '操作', key: 'action',
                render: (text: string, record: IMgrAccGroup) => (
                    <div>
                        <Button type="danger" onClick={() => this.delConfirm(record.id)}>删除</Button>
                        <Button type="primary" onClick={() => this.chgAccGroup(record.id)}>修改</Button>
                    </div>
                ),
            },
        ];

        return (
            <div>
                <Row>
                    <Col span={24}>
                        <Button type="primary" onClick={() => {
                            this.addAccGroup();
                        }}>添加新组</Button>
                    </Col>
                </Row>

                <Row>
                    <Col span={24}>
                        <Table rowKey="id" pagination={false} columns={columns} dataSource={mgraccgroups}
                               size="small"/>
                    </Col>
                </Row>
            </div>
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(AccGroupMgr);
