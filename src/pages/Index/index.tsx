import { listInterfaceInfoByPage } from '@/services/yuapi-backend/interfaceInfoController';
import { PageContainer } from '@ant-design/pro-components';
import ProList from '@ant-design/pro-list/lib';
import { Card, message } from 'antd';
import React, { useEffect, useState } from 'react';

const Index: React.FC = () => {
  const [dataSource, setDataSource] = useState<API.InterfaceInfo[]>();
  const [total, setTotal] = useState<number>(0);

  const loadingData = async (current = 1, pageSize = 10) => {
    try {
      const res = await listInterfaceInfoByPage({
        current: current,
        pageSize: pageSize,
      });
      setDataSource(res?.data?.records ?? []);
      setTotal(res?.data?.total ?? 0);
    } catch (error: any) {
      message.error('操作失败' + error.message);
    }
  };

  useEffect(() => {
    loadingData();
  }, []);

  return (
    <PageContainer title="接口列表">
      <Card>
        <ProList<API.InterfaceInfo>
          rowKey="id"
          headerTitle="基础列表"
          dataSource={dataSource}
          showActions="hover"
          editable={{
            onSave: async (key, record, originRow) => {
              console.log(key, record, originRow);
              return true;
            },
          }}
          // onDataSourceChange={loadingData}
          metas={{
            title: {
              dataIndex: 'name',
            },
            description: {
              dataIndex: 'description',
            },
            actions: {
              render: (text, row) => {
                return <a href={`/interface_info/${row.id}`}> 查看 </a>;
              },
            },
          }}
          pagination={{
            pageSize: 10,
            showTotal(total: number) {
              return '总数: ' + total;
            },
            total,
            onChange(current, pageSize) {
              loadingData(current, pageSize);
            },
          }}
        />
      </Card>
    </PageContainer>
  );
};

export default Index;
