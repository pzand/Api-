import {
  ProColumns, ProFormInstance,
  ProTable,
} from '@ant-design/pro-components';
import '@umijs/max';
import {Modal} from 'antd';
import React, {useEffect, useRef} from 'react';

export type Props = {
  columns: ProColumns<API.RuleListItem>[],
  onCancel: () => void;
  onSubmit: (values: API.InterfaceInfo) => Promise<void>;
  visible: boolean;
  values: API.InterfaceInfo
};
const UpdateModal: React.FC<Props> = (props: Props) => {
  const {values, visible, columns, onCancel, onSubmit} = props;

  const formRef = useRef<ProFormInstance>();
  useEffect(() => {
    formRef.current?.setFieldsValue(values);
  }, [values])

  return <Modal open={visible} footer={null} onCancel={() => {
    onCancel?.()
  }}>
    <ProTable type={"form"} columns={columns} onSubmit={async (value) => {
      onSubmit?.(value)
    }} formRef={formRef}
    />
  </Modal>;
};
export default UpdateModal;
