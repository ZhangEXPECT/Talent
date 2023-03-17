<template>
	<view class="content">
		<view class="title">
			<text>欢迎进入**</text>
		</view>

		<view class="tabs">
			<u-tabs :list="tabList" bg-color="rgba(0,0,0,0)" font-size="5rpx" bold="false" bar-height="10"
				bar-width="100" :current="current" @change="change"></u-tabs>
			<!-- active-color="#1c2c3f" inactive-color="white" -->
		</view>

		<view class="form" v-if="current==0">
			<view class="input">
				<u-input type='text' v-model="value" placeholder='请输入手机号' @confirm="searchSubmitValue"></u-input>
			</view>
			<view class="input">
				<u-input type='text' placeholder='验证码'></u-input>
			</view>

		</view>
		<view class="form" v-if="current==1">
			<view class="input">
				<u-input type='text' placeholder='请输入账号'></u-input>
			</view>
			<view class="input">
				<u-input type='password' placeholder='请输入密码' password-icon="true"></u-input>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				tabList: [{
					name: '验证码登录'
				}, {
					name: '密码登录'
				}],
				current: 0,
				phoneNumber: 0,
			};
		},
		methods: {

			change(index) {
				this.current = index;
				let id = this.tabList[index].id;
				let name = this.tabList[index].name;
				// uni.navigateTo({
				// 	url: `/pages/goods/goods_list/index?cid=${id}&title=${name}`
				// })
			},

		},
	}
</script>

<style lang="scss" scoped>
	.content {
		background: linear-gradient(to left, #FEEAEA, #E3F4F9);
		background-repeat: no-repeat;
		background-position: center;
		background-size: cover;
		width: 100%;
		height: 1500rpx;
		padding: 65rpx;

		.title {
			height: auto;
			width: 100%;
			font-family: Arial;
			font-size: 38rpx;
			font-weight: bold;
			margin-top: 100rpx;
			margin-bottom: 25rpx;
		}

		.tabs {
			width: 100%;
			height: 80rpx;
			font-size: 30rpx;
			margin-bottom: 40rpx;
			margin-left: -25rpx;
		}

		.form {
			height: 300rpx;
			width: 95%;
			margin: 0 auto;
			display: flex;
			flex-direction: column;
			justify-content: space-around;
			align-items: center;

			.input {
				width: 100%;
				height: 90rpx;
				box-sizing: border-box;
				border: 1rpx solid lightgray;
				font-size: 32rpx;
				padding-left: 25rpx;
				padding-top: 10rpx;
				padding: 10rpx 50rpx 0rpx 25rpx;
				font-size: 32rpx;
			}
		}
	}
</style>
