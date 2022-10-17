// document.addEventListener("DOMContentLoaded", function () {
//     console.log("hello");
//     console.log('hello');
// })
// request();
// const request = async () => {
    console.log('hello');
    let promise = await fetch('http://localhost:8080/api/request');
    if (promise.ok) {
        let json = promise.json();
        console.log(json);
    }else {
        alert("fail" + promise)
    }
// }
// request().then(r => );
// addEventListener("DOMContentLoaded", function () {
//
//     getRequest();
// })
// console.log("hello");
// console.log('hello');
// document.addEventListener("DOMContentLoaded",function (){
//     getRequest();
// // })
// const getRequest = () => {
//     console.log('getRequest')
//     fetch('http://localhost:8080/api/request',{
//             method: 'GET'
//         }
//
//         )
//         .then(function (value) {
//             if (value !== 200) {
//                 return Promise.reject(new Error(value.status))
//             }
//             return value.json()
//         })
//         .then(function (output){
//        JSON.stringify(output)
//             console.log(output)
//     })
//         .catch(function (reason) {
//             console.log('error' + reason)
//         })
//     ;
//
// }