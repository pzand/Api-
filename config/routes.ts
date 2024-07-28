export default [
  { path: '/', name: '主页', icon: 'smile', component: './Index' },
  {
    path: '/interface_info/:id',
    name: '查看接口',
    icon: 'smile',
    component: './InterfaceInfo',
    hideInMenu: true,
  },
  {
    path: '/user',
    layout: false,
    routes: [{ name: '登录', path: '/user/login', component: './User/Login' }],
  },
  {
    path: '/admin',
    name: '管理页',
    icon: 'crown',
    access: 'canAdmin',
    routes: [
      {
        name: '查询表格',
        icon: 'table',
        path: '/admin/Interface_info',
        component: './Admin/InterfaceInfo',
      },
      {
        name: '接口分析',
        icon: 'analysis',
        path: '/admin/Interface_analysis',
        component: './Admin/InterfaceAnalysis',
      },
    ],
  },
  // {path: '/', redirect: '/welcome'},
  { path: '*', layout: false, component: './404' },
];
