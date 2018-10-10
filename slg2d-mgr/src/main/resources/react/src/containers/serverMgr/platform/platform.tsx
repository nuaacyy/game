import * as React from 'react';
import { connect } from 'react-redux';

import { bindActionCreators, Dispatch } from 'redux';
import { Button, Col, Modal, Row, Table } from 'antd';
import { History } from 'history';

import { deletePlatform, IDelPlatformData } from '../../../sagas/serverMgr/platformMgr';
import { ICombineState } from '../../../redux/reducer';
import { ADD_PLATFORM, CHG_PLATFORM } from '../../../const/funcType';
import { IPlatform } from '../../../redux/modules/serverMgr/platform';
import { IReqAction } from '../../../sagas/ibaseaction';

const ButtonGroup = Button.Group;

const confirm = Modal.confirm;

interface IStateProps {
    history: History;
    platforms: IPlatform[];
}

interface IDispatchProps {
    deletePlatform(id: number): IReqAction<IDelPlatformData>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        platforms: state.serverMgr.platform.platforms,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        deletePlatform,
    }, dispatch);
};

interface IState {

}

class PlatformMgr extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        this.state = {};
    }

    delConfirm = (recordId: number) => {
        confirm({
            title: '删除平台配置',
            content: '你确定要删除这条记录？',
            onOk: () => {
                this.props.deletePlatform(recordId);
            },
        });
    };

    chgPlatform = (path: string) => {
        this.props.history.push(path);
    };

    switch2Add = () => {
        this.props.history.push(ADD_PLATFORM);
    };

    render() {
        const platfroms = this.props.platforms;
        const total = platfroms.length;
        const pagination = {
            total,
            pageSize: 15,
        };

        const columns = [
            {title: 'ID', dataIndex: 'id', key: 'id'},
            {title: '名称', dataIndex: 'platformName', key: 'platformName'},
            {title: 'opId', dataIndex: 'opId', key: 'opId'},
            {title: 'gameId', dataIndex: 'gameId', key: 'gameId'},
            {
                title: '操作', key: 'action',
                render: (text: string, record: IPlatform) => (
                    <div>
                        <ButtonGroup>
                            <Button type="primary" onClick={() => {
                                this.chgPlatform(CHG_PLATFORM + '/' + record.id);
                            }}>修改</Button>
                            <Button type="danger" onClick={() => {
                                this.delConfirm(record.id);
                            }}>删除</Button>
                        </ButtonGroup>
                    </div>
                ),
            },
        ];

        return (
            <div>
                <Row>
                    <Col span={24} className="col-sm-offset-5">
                        <Button type="primary" onClick={this.switch2Add}>添加新平台</Button>
                    </Col>
                </Row>

                <Row>
                    <Col span={24}>
                        <Table rowKey="id" pagination={pagination} columns={columns} dataSource={platfroms}
                               size="small"/>
                    </Col>
                </Row>

            </div>
        );
    }

}

export default connect(mapStateToProps, mapDispatchToProps)(PlatformMgr);