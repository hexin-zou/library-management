// axios配置文件
import axios from 'axios';
import router from "@/router";
import Cookies from "js-cookie";

const request = axios.create({
    baseURL: 'http://localhost:9090',
    timeout: 5000
});

// request 拦截器
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    const user = Cookies.get('admin');
    // if (!user) {
    //     // 检查当前路由是否已经是登录页面
    //     if (router.currentRoute.path !== '/login') {
    //         router.push('/login');
    //         // return Promise.reject(new Error('No user information')); // 阻止请求继续发送
    //     } else {
    //         config.headers['Authorization'] = user;
    //     }
    // }
    config.headers['Authorization'] = user;
    return config;
}, error => {
    return Promise.reject(error);
});

// response 拦截器
request.interceptors.response.use(
    response => {
        let res = response.data;
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res;
        }
        return res;
    },
    error => {
        console.log('err' + error); // for debug
        return Promise.reject(error);
    }
);

export default request;