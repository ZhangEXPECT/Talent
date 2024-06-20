<template>
	<view class="progress">
		<view class="tabs">
			<u-tabs :list="list" :is-scroll="false" :current="current" font-size="30" active-color="#2979ff"
				inactive-color="#606266" bar-height="6" bar-width="100" @change="change">
			</u-tabs>
		</view>

		<view class="content_first" v-if="current===0">
			<view class="apartment" v-if="qualificationList.length">
				<view class="item" v-for="(item,index) in qualificationList" :key="index">
					<view class="top">
						<view class="buildTime">
							{{item.buildTime}}
						</view>
						<u-section :title="item.date" sub-title="查看详情" :show-line="false" :bold="false">
						</u-section>
					</view>
					<view class="bottom">
						当前状态：{{item.status}}						
					</view>
					<view class="delete" @click="deleteSubsidy(item.qualificationId)">
						<button type="warn">删除申请</button>
					</view>
				</view>
			</view>
			<view class="default" v-else>
				<image src="/static/images/default/缺省页-空空如也.png" mode="aspectFit"></image>
			</view>
		</view>

		<view class="content_second" v-if="current===1">
			<view class="apartment" v-if="subsidyList.length">
				<view class="item" v-for="(item,index) in subsidyList" :key="index" >
					<view class="top">
						<view class="buildTime">
							{{item.buildTime}}
						</view>
						<u-section :title="item.date" sub-title="查看详情" :show-line="false" :bold="false">
						</u-section>
					</view>
					<view class="bottom">
						当前状态：{{item.status}}						
					</view>
					<view class="delete" @click="deleteSubsidy(item.subsidyId)">
						<button type="warn">删除申请</button>
					</view>
				</view>
			</view>
			<view class="default" v-else>
				<image src="/static/images/default/缺省页-空空如也.png" mode="aspectFit"></image>
			</view>

		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				list: [{
					name: '人才公寓申请进度'
				}, {
					name: '租房补贴申请进度'
				}, ],
				current: 0,
				qualificationList: [],
				subsidyList: [],
				userId: 10001
			};
		},
		onShow() {
			this.getQualification(this.userId);
			this.getSubsidy(this.userId);
		},
		methods: {

			change(index) {
				this.current = index;
			},

			getQualification(userId) {
				let that = this;
				uni.request({
					url: `http://localhost:8899/qualification/queryByUserId/${userId}`,
					method: 'get',
					dataType: 'json',
					success: (res) => {
						console.log(res.data.data);
						that.qualificationList.push(res.data.data);
					},
				});
			},
			getSubsidy(userId) {
				let that = this;
				uni.request({
					url: `http://localhost:8899/subsidy/queryByUserId/${userId}`,
					method: 'get',
					dataType: 'json',
					success: (res) => {
						that.subsidyList.push(res.data.data);
					},
				});
			},
			deleteSubsidy(subsidyId) {
				uni.request({
					url: `http://localhost:8899/subsidy/deleteSubsidy/${subsidyId}`,
					method: 'get',
					dataType: 'json',
					success: (res) => {
						console.log(res.data);
						uni.showToast({
							icon: "success",
							duration: 1000
						})
					},
				});
			},
			deleteQualification(id) {
				uni.request({
					url: `http://localhost:8899/subsidy/deleteSubsidy/${id}`,
					method: 'get',
					dataType: 'json',
					success: (res) => {
						console.log(res.data);
						uni.showToast({
							icon: "success",
							duration: 1000
						})
					},
				});
			},

			toPublicDetail(id) {
				uni.navigateTo({
					url: `/pages/policy/public/index?policyId=${id}`
				})
			},

		},
	}
</script>

<style lang="scss" scoped>
	.progress {
		background-color: #FFFFFF;
	}

	.tabs {
		margin-top: -15rpx;
		font-weight: bold;
	}

	.content_first {
		height: 10000rpx;


		.apartment {
			width: 95%;
			height: auto;
			margin: 0 auto;
			display: flex;
			flex-direction: column;
			margin-top: -20rpx;


			.item {
				height: 250rpx;
				width: 95%;
				margin: 0 auto;
				border-radius: 15rpx;
				background-color: #eaeaea;
				margin-bottom: 20rpx;
				padding: 15rpx;
				z-index: 1;
				margin-top: 35rpx;

				.top {
					height: 50%;
					padding: 15rpx;					
					display: flex;
					justify-content: space-between;
				}

				.bottom {
					height: 50%;
					padding: 15rpx;
					margin-top: 5rpx;
					font-size: 32rpx;
					font-family: Arial, Helvetica, sans-serif;

				}


				.delete {
					position: absolute;
					right: 60rpx;
					top: 20%;
					
					button{
						font-size: 28rpx;
						padding: 5rpx;
					}
				}
			}
		}

	}


	.content_second {
		.apartment {
			width: 95%;
			height: auto;
			margin: 0 auto;
			display: flex;
			flex-direction: column;
			margin-top: -20rpx;
		
		
			.item {
				height: 250rpx;
				width: 95%;
				margin: 0 auto;
				border-radius: 15rpx;
				background-color: #eaeaea;
				margin-bottom: 20rpx;
				padding: 15rpx;
				z-index: 1;
				margin-top: 35rpx;
		
				.top {
					height: 50%;
					padding: 15rpx;					
					display: flex;
					justify-content: space-between;
				}
		
				.bottom {
					height: 50%;
					padding: 15rpx;
					margin-top: 5rpx;
					font-size: 32rpx;
					font-family: Arial, Helvetica, sans-serif;
		
				}
		
				.delete {
					position: absolute;
					right: 60rpx;
					top: 20%;
					
					button{
						font-size: 28rpx;
						padding: 5rpx;
					}
				}
			}
		}

	}


	.default {
		position: absolute;
		height: 100%;
		width: 100%;
		background-color: #FFFFFF;

		image {
			width: 100%;
			height: 85%;
		}
	}
</style>
