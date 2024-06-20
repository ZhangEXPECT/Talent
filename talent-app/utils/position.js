
//位置授权
export function getAuthorize () {
  return new Promise((resolve, reject) => {
    uni.authorize({
      scope: 'scope.userLocation',
      success: () => {
        // 1 用户允许授权
        // 2 用户之前已经同意授权，则不会出现弹窗，直接返回成功
        //  以上两种情况都会进入到success回调中
        resolve() // 允许授权
      },
      fail: () => {
        // 1 用户拒绝授权
        // 2 用户之前拒绝了授权，此接口会直接进入失败回调
        //  以上两种情况都会进入到fail回调中
        reject() // 拒绝授权
      },
    })
  })
}

