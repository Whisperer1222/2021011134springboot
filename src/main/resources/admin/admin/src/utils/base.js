const base = {
    get() {
        return {
            url : "http://localhost:9999/springboot4r3y8/",
            name: "springboot4r3y8",
            // 退出到首页链接
            indexUrl: 'http://localhost:9999/springboot4r3y8/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "二手销售平台"
        } 
    }
}
export default base
