<template>
	<view class="container">

		<!-- 搜索 -->
		<view class="search">
			<view class='notice'>
				<view class='serch'>
					<navigator url="/pages/house/search/index" class="input" hover-class="none"><text
							class="iconfont icon-xiazai5"></text>
						<text style="margin-left:15rpx;color: #808080;font-size: 30rpx;letter-spacing:5rpx">搜索公寓</text>
					</navigator>
				</view>
				<view class="message" @tap="toMessage">
					<image src="/static/images/消息2.png" mode="aspectFit"></image>
				</view>
			</view>
		</view>

		<!-- 人才公寓 -->
		<view class="apartment">
			<view class="title">
				<u-section title="人才公寓" :right="false"></u-section>
			</view>
			<view class="menu">
				<view class='nav acea-row' v-if="menus1.length">
					<block v-for="(item,index) in menus1" :key="index">
						<navigator class='item' :url='item.url' hover-class='none'>
							<view class='pictrue'>
								<image :src='item.pic' mode="aspectFill"></image>
							</view>
							<view class="menu-txt">{{item.name}}</view>
						</navigator>
					</block>
				</view>
			</view>
		</view>
		<!-- 补贴 -->
		<view class="subsidy">
			<view class="title">
				<u-section title="租房补贴" :right="false"></u-section>
			</view>
			<view class="menu">
				<view class='nav acea-row' v-if="menus2.length">
					<block v-for="(item,index) in menus2" :key="index">
						<navigator class='item' :url='item.url' hover-class='none'>
							<view class='pictrue'>
								<image :src='item.pic' mode="aspectFill"></image>
							</view>
							<view class="menu-txt">{{item.name}}</view>
						</navigator>
					</block>
				</view>
			</view>
		</view>
		<!-- 房屋服务 -->
<!-- 		<view class="service">
			<view class="title">
				<u-section title="房屋服务" :right="false"></u-section>
			</view>
			<view class="menu">
				<view class='nav acea-row' v-if="menus3.length">
					<block v-for="(item,index) in menus3" :key="index">
						<navigator class='item' :url='item.url' hover-class='none'>
							<view class='pictrue'>
								<image :src='item.pic' mode="aspectFill"></image>
							</view>
							<view class="menu-txt">{{item.name}}</view>
						</navigator>
					</block>
				</view>
			</view>
		</view> -->

	</view>
</template>

<script>
	var statusBarHeight = uni.getSystemInfoSync().statusBarHeight + 'px';
	var statusBarTop = uni.getSystemInfoSync().statusBarHeight + 'px';
	export default {
		data() {
			return {
				statusBarHeight: statusBarHeight,
				statusBarTop: statusBarTop,
				menus1: [
					{
						url: '/pages/subsidy/index',
						pic: '/static/images/service/资格.png',
						name: '公寓申请'
					},
					{
						url: `/pages/house/index?houseId=10001`,
						pic: '/static/images/service/预约.png',
						name: '预约看房'
					},
					{
						url: '/pages/progress/index',
						pic: '/static/images/进度.png',
						name: '公寓申请'
					},
					{
						url: '/pages/policy/index',
						pic: '/static/images/service/申请进度.png',
						name: '申请进度'
					},
					{
						url: '/pages/policy/index',
						pic: '/static/images/service/政策指引.png',
						name: '政策指引'
					},
					{
						url: '/pages/policy/index',
						pic: '/static/images/service/申请指南.png',
						name: '申请指南'
					},
				],
				menus2: [{
						url: '/pages/house/application/index',
						pic: '/static/images/service/资格.png',
						name: '补贴申请'
					},
					{
						url: '/pages/house/qualification/index',
						pic: '/static/images/service/预约.png',
						name: '申请进度'
					},
				],
				menus3: [{
						url: '/pages/house/application/index',
						pic: '/static/images/service/资格.png',
						name: '入住登记'
					},
					{
						url: '/pages/house/qualification/index',
						pic: '/static/images/service/预约.png',
						name: '同住人登记'
					},
					{
						url: '/pages/progress/index',
						pic: '/static/images/service/报修申请.png',
						name: '报修申请'
					},
					{
						url: '/pages/policy/index',
						pic: '/static/images/service/申请进度.png',
						name: '退租申请'
					},
					{
						url: '/pages/policy/index',
						pic: '/static/images/service/政策指引.png',
						name: '换房申请'
					},
					{
						url: '/pages/policy/index',
						pic: '/static/images/service/申请指南.png',
						name: '房屋续租'
					},
				]
			};
		}
	}
</script>

<style lang="scss" scoped>
	.container {
		margin: 0;
		padding: 0;
		background-color: #f7f7f7;

	}

	.search {
		height: auto;
		width: 100%;
		position: relative;
		// top: 170rpx;
		// margin-top: -23rpx;
		margin-bottom: 25rpx;
		overflow: hidden;
		background-color: #FFFFFF;


		.notice {
			margin-left: 25rpx;
			width: 80%;
			height: 70rpx;
			border-radius: 35rpx;
			background-color: #f5f5f5;
			margin-bottom: 10rpx;
			margin-top: 20rpx;
			line-height: 63rpx;
			padding: 5rpx 14rpx;
			position: relative;
			z-index: 2;
			display: flex;

			.serch {
				margin-left: 15rpx;
				width: 80%;
				display: flex
			}
		}

		.message {
			width: 70rpx;
			height: 70rpx;
			position: absolute;
			right: -100rpx;

			image {

				width: 100%;
				height: 100%;
			}
		}
	}


	.title {
		height: 50rpx;
		width: 100%;
		padding: 30rpx;
		margin-top: 15rpx;

		/deep/uni-text {
			color: rgb(46, 101, 210) !important;
		}
	}




	.menu {
		height: 95%;
		width: 95%;
		margin: 0rpx auto;
		border-radius: 15rpx;
		margin-bottom: 15rpx;
		margin-top: 15rpx;

		.nav {
			padding-bottom: 26rpx;
			margin-bottom: 15rpx;
			opacity: 1;
			border-radius: 14rpx;
			width: 100%;


			.item {
				display: flex;
				flex-direction: column;
				align-items: center;
				justify-content: center;
				width: 20%;
				margin-top: 35rpx;

				image {
					width: 50rpx;
					height: 50rpx;
				}

				.menu-txt {
					font-size: 24rpx; 
				}
			}
		}
	}

	.apartment {
		background-color: #FFFFFF;
		height: 350rpx;
		width: 100%;
		margin-bottom: 20rpx;

	}

	.subsidy {
		background-color: #FFFFFF;
		height: 230rpx;
		width: 100%;
		margin-bottom: 20rpx;


	}

	.service {
		background-color: #FFFFFF;
		height: 350rpx;
		width: 100%;

	}
</style>
