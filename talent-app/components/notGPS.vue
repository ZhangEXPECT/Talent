<template>
	<view class="openGps">
		<image src="/static/images/noAddress.png" class="empty-img"></image>
		<view class="empty-txt">
			为给您提供最佳服务，小程序需要获取您的当前位置以定位您附近的商户
		</view>
		<view class="handleBtn" @tap="gotoLocation">点击开启定位</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {}
		},
		methods: {
			//打开小程序授权地理位置设置，不管用户操作是否授权，返回父组件页面之后，都会触发onShow周期函数里的代码

			gotoLocation() {
				// #ifdef MP
				uni.openSetting({
					success(res) {
						console.log(res.authSetting)
					},
				})
				// #endif
			},
		},
	}
</script>

<style lang="scss">
	.openGps {
		text-align: center;
		padding: 400rpx 80rpx 0 80rpx;
	}

	.empty-img {
		width: 380rpx;
		height: 284rpx;
	}

	.empty-txt {
		font-size: 28rpx;
		color: #999;
	}

	.handleBtn {
		background: linear-gradient(-10deg, #00ca20, #02bb34);
		font-size: 20rux;
		color: #fff;
		font-weight: bold;
		width: 300upx;
		text-align: center;
		height: 70upx;
		line-height: 70upx;
		border-radius: 35upx;
		margin: 30upx auto;
	}
</style>
