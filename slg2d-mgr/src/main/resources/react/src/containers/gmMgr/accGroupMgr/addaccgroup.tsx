import * as React from 'react';
import { ChangeEvent } from 'react';
import { connect } from 'react-redux';

import { bindActionCreators, Dispatch } from 'redux';
import { Button, Checkbox, Col, Form, Input, Radio, Row, Table } from 'antd';
import { addAccGroup } from '../../../sagas/accGroupMgr';
import { History } from 'history';
import { ICombineState } from '../../../redux/reducer';
import { ACC_GROUP_MGR } from '../../../const/funcType';
import { IMgrAccGroupRight } from '../../../redux/modules/accGroup';
import { IPlatform } from '../../../redux/modules/serverMgr/platform';
import { IReqAction } from '../../../sagas/ibaseaction';
import { RadioChangeEvent } from 'antd/lib/radio';

const FormItem = Form.Item;
const RadioGroup = Radio.Group;

interface IStateProps {
    history: History;
    accRights: IMgrAccGroupRight[];
    platforms: IPlatform[];
}

interface IDispatchProps {
    addAccGroup(data: {}): IReqAction<{}>;
}

type IAppProps = IStateProps & IDispatchProps;

const mapStateToProps = (state: ICombineState) => {
    return {
        accRights: state.accGroup.accRights,
        platforms: state.serverMgr.platform.platforms,
    };
};

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        addAccGroup,
    }, dispatch);
};

class RightCheckBox {
    id: number;
    checked: boolean;
    info: string;
}

interface IState {
    isQa: number;
    groupState: number;
    rights: RightCheckBox[];
    rightMap: Map<number, RightCheckBox>;
    groupName: string;
    platforms: number[];
    title: string;
    body: string;
}

class AddAccountGroup extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        const rightList: RightCheckBox[] = [];
        const rightMap = new Map<number, RightCheckBox>();
        const accRights = this.props.accRights;
        accRights.map((accRight: IMgrAccGroupRight) => {
            const right = {
                id: accRight.id,
                info: accRight.info,
                checked: false,
            };
            rightMap[accRight.id] = right;
            rightList.push(right);
        });

        this.state = {
            isQa: 1,
            groupState: 1,
            rights: rightList,
            rightMap,
            groupName: '',
            platforms: [],
            title: '',
            body: '',
        };
    }

    cancelAdd = () => {
        this.props.history.push(ACC_GROUP_MGR);
    };

    handleSubmit = () => {
        const data = {
            isQa: this.state.isQa,
            groupState: this.state.groupState,
            rights: this.state.rights,
            groupName: this.state.groupName,
            platforms: this.state.platforms,
        };

        this.props.addAccGroup(data);

    };

    updateGroupName = (event: ChangeEvent<HTMLInputElement>) => {
        const groupName = event.target.value;
        this.setState({
            groupName,
        });
    };

    updateIsQA = (event: RadioChangeEvent) => {
        const isQa = parseInt(event.target.value, 10);
        this.setState({
            isQa,
        });
    };

    updateGroupState = (event: RadioChangeEvent) => {
        const groupState = parseInt(event.target.value, 10);
        this.setState({
            groupState,
        });
    };

    clickRights = (event: RadioChangeEvent, rightId: number) => {
        const isChecked = event.target.checked;
        const rightMap = this.state.rightMap;
        if (isChecked) {
            rightMap[rightId].checked = true;
        } else {
            rightMap[rightId].checked = false;
        }

        this.setState({
            rightMap,
        });
    };

    clickPlatform = (event: RadioChangeEvent, platformId: number) => {
        const isChecked = event.target.checked;
        const newList = this.state.platforms;
        if (isChecked === true) {
            newList.push(platformId);
            this.setState({
                platforms: newList,
            });

        } else {
            for (let i = 0; i < newList.length; i++) {
                if (newList[i] === platformId) {
                    newList.splice(i, 1);
                }
            }
        }
    };

    render() {
        const formItemLayout = {
            labelCol: {span: 4},
            wrapperCol: {span: 10},
        };
        const tailFormItemLayout = {
            wrapperCol: {
                offset: 4,
                span: 10,
            },
        };

        // 平台表
        const allPlatforms = this.props.platforms;

        const pltColumns = [
            {
                title: '平台', dataIndex: 'id', key: 'id',
                render: (text: string, record: IPlatform) => (
                    <Checkbox
                        onChange={(event: RadioChangeEvent) => this.clickPlatform(event, record.id)}>
                        {record.platformName}
                    </Checkbox>
                ),
            },
        ];

        // 权限表
        const rightsColumns = [
            {
                title: '描述', dataIndex: 'id', key: 'id',
                render: (text: string, record: RightCheckBox) => (
                    <Checkbox checked={record.checked}
                              onChange={(event: RadioChangeEvent) => this.clickRights(event, record.id)}>
                        {record.info}
                    </Checkbox>
                ),
            },
        ];

        return (
            <Row>
                <Col span={8}>
                    <Form>
                        <FormItem {...formItemLayout} label="组名">
                            <Input value={this.state.groupName} onChange={this.updateGroupName}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="是否QA">
                            <RadioGroup onChange={this.updateIsQA} value={this.state.isQa}>
                                <Radio value={1}>是</Radio>
                                <Radio value={2}>否</Radio>
                            </RadioGroup>
                        </FormItem>

                        <FormItem {...formItemLayout} label="组状态">
                            <RadioGroup onChange={this.updateGroupState} value={this.state.groupState}>
                                <Radio value={1}>启用</Radio>
                                <Radio value={2}>关闭</Radio>
                            </RadioGroup>
                        </FormItem>

                        <FormItem {...tailFormItemLayout}>
                            <Button type="primary" onClick={this.handleSubmit}>添加</Button>
                            <Button type="primary" onClick={this.cancelAdd}>取消</Button>
                        </FormItem>
                    </Form>
                </Col>

                <Col span={8}>
                    <Table rowKey="id" pagination={false} columns={pltColumns} dataSource={allPlatforms} size="small"/>
                </Col>

                <Col span={8}>
                    <Table rowKey="id" pagination={false} columns={rightsColumns} dataSource={this.state.rights}
                           size="small"/>
                </Col>
            </Row>
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(AddAccountGroup);