import {
  getInterfaceInfoById,
  invokeInterfaceInfo,
} from '@/services/yuapi-backend/interfaceInfoController';
import { useParams } from '@@/exports';
import { PageContainer } from '@ant-design/pro-components';
import { Button, Card, Descriptions, DescriptionsProps, Divider, Form, Input, message } from 'antd';
import React, { useEffect, useState } from 'react';

const Index: React.FC = () => {
  const [data, setData] = useState<API.InterfaceInfo>();
  const [invokeRes, setInvokeRes] = useState<any>();
  const params = useParams();

  const onFinish = async (values: any) => {
    if (!params.id) {
      message.error('接口不存在');
      return;
    }

    try {
      const res = await invokeInterfaceInfo({
        id: params.id,
        ...values,
      });
      setInvokeRes(res.data);
      message.success('请求成功');
    } catch (e: any) {
      message.error('操作失败' + e.message);
    }
  };

  const loadingData = async () => {
    if (!params.id) {
      message.error('参数不存在');
    }

    try {
      const res = await getInterfaceInfoById({
        id: Number(params.id),
      });
      setData(res.data);
    } catch (error: any) {
      message.error('操作失败' + error.message);
    }
  };

  useEffect(() => {
    loadingData();
  }, []);

  const items: DescriptionsProps['items'] = [
    {
      key: '1',
      label: '接口状态',
      children: <p>{data?.status ? '开启' : '关闭'}</p>,
    },
    {
      key: '2',
      label: '描述',
      children: <p>{data?.description}</p>,
    },
    {
      key: '3',
      label: '请求地址',
      children: <p>{data?.url}</p>,
    },
    {
      key: '4',
      label: '请求方法',
      children: <p>{data?.method}</p>,
    },
    {
      key: '5',
      label: '请求参数',
      children: <p>{data?.requestParams}</p>,
    },
    {
      key: '6',
      label: '请求头',
      children: <p>{data?.requestHeader}</p>,
    },
    {
      key: '7',
      label: '响应头',
      children: <p>{data?.responseHeader}</p>,
    },
    {
      key: '8',
      label: '创建时间',
      children: <p>{data?.createTime}</p>,
    },
    {
      key: '9',
      label: '更新时间',
      children: <p>{data?.updateTime}</p>,
    },
  ];

  return (
    <PageContainer title="查看接口文档">
      <Card>
        <Descriptions title="User Info" items={items} column={1} />
      </Card>

      <Divider />
      <Card title={'在线测试'}>
        <Form name="invoke" layout={'vertical'} onFinish={onFinish} autoComplete="off">
          <Form.Item label="请求参数" name="userRequestParams">
            <Input.TextArea />
          </Form.Item>

          <Form.Item wrapperCol={{ span: 16 }}>
            <Button type="primary" htmlType="submit">
              调用
            </Button>
          </Form.Item>
        </Form>
      </Card>

      <Divider />
      <Card title={'返回结果'}>{invokeRes}</Card>
    </PageContainer>
  );
};

export default Index;
