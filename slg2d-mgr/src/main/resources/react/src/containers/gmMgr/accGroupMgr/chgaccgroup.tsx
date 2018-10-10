import * as React from 'react';
import { ChangeEvent } from 'react';
import { connect } from 'react-redux';
import { match } from 'react-router';
import { bindActionCreators, Dispatch } from 'redux';
import { Button, Checkbox, Col, Form, Input, Radio, Row, Table } from 'antd';
import { chgAccGroup } from '../../../sagas/accGroupMgr';
import { History } from 'history';
import { ICombineState } from '../../../redux/reducer';
import { ACC_GROUP_MGR } from '../../../const/funcType';
import { IMgrAccGroup, IMgrAccGroupRight } from '../../../redux/modules/accGroup';
import { IPlatform } from '../../../redux/modules/serverMgr/platform';
import { IReqAction } from '../../../sagas/ibaseaction';
import { RadioChangeEvent } from 'antd/lib/radio';

const FormItem = Form.Item;
const RadioGroup = Radio.Group;

interface IChgAccGroupParam {
    gId: string;
}

interface IStateProps {
    history: History;
    match: match<IChgAccGroupParam>;

    platforms: IPlatform[];
    accRights: IMgrAccGroupRight[];
    mgraccgroups: IMgrAccGroup[];
}

interface IDispatchProps {
    chgAccGroup(data: {}): IReqAction<{}>;
}

type IAppProps = IStateProps & IDispatchProps;

const mapStateToProps = (state: ICombineState) => {
    return {
        platforms: state.serverMgr.platform.platforms,
        accRights: state.accGroup.accRights, // 所有权限
        mgraccgroups: state.accGroup.mgraccgroups, // 当前权限
    };
};

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        chgAccGroup,
    }, dispatch);
};

// 检查用户组和所有权限比对的类
class RightCheckBox {
    id: number;
    checked: boolean;
    info: string;
}

// 检查用户组和所有权限比对的类
class PlatformCheckBox {
    id: number;
    checked: boolean;
    platformName: string;
}

interface IState {
    id: number;
    isQa: number;
    groupState: number;
    allRights: RightCheckBox[];
    allRightMap: Map<number, RightCheckBox>;
    allPlatformList: PlatformCheckBox[];
    allPlatformMap: Map<number, PlatformCheckBox>;
    groupName: string;
    platforms: number[];
}

class ChgAccountGroup extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        // 用户组id
        const targetAccountGroupId = parseInt(this.props.match.params.gId, 10);
        let targetAccountGroup = null;
        for (const group of this.props.mgraccgroups) {
            if (group.id === targetAccountGroupId) {
                // 当前组
                targetAccountGroup = group;
            }
        }

        // 生成权限列表和字典
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

        if (!(targetAccountGroup == null || targetAccountGroup === undefined)) {
            targetAccountGroup.rights.map((eachRight: IMgrAccGroupRight) => {
                const targetRight = rightMap[eachRight.id];
                if (targetRight === undefined) {
                    return;
                }
                targetRight.checked = true;
            });
        }

        // 重新生成带checked的平台列表
        const allPlatformList: PlatformCheckBox[] = [];
        const allPlatformMap = new Map<number, PlatformCheckBox>();
        const allPlatforms = this.props.platforms;

        allPlatforms.map((allPlatform: IPlatform) => {
            const checkedPlatform = {
                id: allPlatform.id,
                platformName: allPlatform.platformName,
                checked: false,
            };
            allPlatformList.push(checkedPlatform);
            allPlatformMap[allPlatform.id] = checkedPlatform;
        });

        if (!(targetAccountGroup == null || targetAccountGroup === undefined)) {
            targetAccountGroup.platforms.map((platform: number) => {
                const targetPlatform = allPlatformMap[platform];
                if (targetPlatform === undefined) {
                    //
                } else {
                    targetPlatform.checked = true;
                }
            });

            this.state = {
                id: targetAccountGroupId,
                isQa: targetAccountGroup.isQa,
                groupState: targetAccountGroup.groupState,
                allRights: rightList,
                allRightMap: rightMap,
                allPlatformList,
                allPlatformMap,
                groupName: targetAccountGroup.groupName,
                platforms: targetAccountGroup.platforms,
            };
        } else {
            this.state = {
                id: 0,
                isQa: 0,
                groupState: 0,
                allRights: rightList,
                allRightMap: rightMap,
                allPlatformList,
                allPlatformMap,
                groupName: '',
                platforms: [],
            };
        }

    }

    cancelChange = () => {
        this.props.history.push(ACC_GROUP_MGR);
    };

    handleSubmit = () => {
        const data = {
            isQa: this.state.isQa,
            groupId: this.state.id,
            groupState: this.state.groupState,
            rights: this.state.allRights,
            groupName: this.state.groupName,
            platforms: this.state.platforms,
        };

        this.props.chgAccGroup(data);
    };

    // 更新组名
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
        const rightMap = this.state.allRightMap;
        if (isChecked) {
            rightMap[rightId].checked = true;
        } else {
            rightMap[rightId].checked = false;
        }

        this.setState({
            allRightMap: rightMap,
        });
    };

    clickPlatform = (event: RadioChangeEvent, platformId: number) => {
        const isChecked = event.target.checked;
        const allPlatformMap = this.state.allPlatformMap;
        if (isChecked) {
            allPlatformMap[platformId].checked = true;
        } else {
            allPlatformMap[platformId].checked = false;
        }
        this.setState({
            allPlatformMap,
        });
        const newList = this.state.platforms;
        if (isChecked) {
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

        const pltColumns = [
            {
                title: '平台', key: 'id',
                render: (text: string, record: PlatformCheckBox) => (
                    <Checkbox checked={record.checked}
                              onChange={(event: RadioChangeEvent) => this.clickPlatform(event, record.id)}>
                        {record.platformName}
                    </Checkbox>
                ),
            },
        ];

        const rightsColumns = [
            {
                title: '描述', key: 'id',
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
                        <FormItem {...formItemLayout} label="ID">
                            <Input value={this.state.id} disabled={true}/>
                        </FormItem>

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
                            <Button type="primary" onClick={this.handleSubmit}>修改</Button>
                            <Button type="primary" onClick={this.cancelChange}>取消</Button>
                        </FormItem>
                    </Form>
                </Col>

                <Col span={8}>
                    <Table rowKey="id" pagination={false} columns={pltColumns} dataSource={this.state.allPlatformList}
                           size="small"/>
                </Col>

                <Col span={8}>
                    <Table rowKey="id" pagination={false} columns={rightsColumns} dataSource={this.state.allRights}
                           size="small"/>
                </Col>
            </Row>
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(ChgAccountGroup);
