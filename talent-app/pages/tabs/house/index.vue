<template>
	<view>
		<!-- 搜索框 -->
		<view class="search" >
			<view class='input acea-row row-between-wrapper'><text class='iconfont icon-sousuo'></text>
				<input placeholder='搜索小区名称' placeholder-class='placeholder' confirm-type='search' name="search"
					:value='input.keyword' @confirm="searchSubmit"></input>
			</view>
			<view class="message" @tap="toMessage">
				<image src="/static/images/消息2.png" mode="aspectFit"></image>
			</view>

		</view>
		<!-- 筛选 -->

		<view class='nav acea-row row-middle'>
			<u-dropdown :dropDownShow="dropDownShow" @open="open" @close="close">
				<u-dropdown-item v-model="value1" title="区域" :options="brandList" @change="changeBrand">
				</u-dropdown-item>
				<u-dropdown-item v-model="value2" title="户型" :options="styleList" @change="changestyle">
				</u-dropdown-item>
				<u-dropdown-item v-model="value3" title="租金" :options="styleList" @change="changestyle">
				</u-dropdown-item>
				<u-dropdown-item v-model="value4" title="筛选" :options="styleList" @change="changestyle">
				</u-dropdown-item>
			</u-dropdown>

		</view>


		<view class="">

		</view>
		<view class="house">
			<view class="houseList">
				<view class="item" v-for="item in houseList" :key="item.index">
					<image :src="item.url" mode="aspectFill"></image>
					<view class="detail">
						<view class="name">
							{{item.name}}
						</view>
						<view class="district">
							<text>{{item.district}}</text>
							<text>/{{item.stock}}套</text>
						</view>
						<view class="advantage">
							<view class="itemn" v-for="itemn in item.advantage" :key="itemn.index">
								{{itemn}}
							</view>
						</view>
						<view class="price">
							{{item.price[0]}}
							<block v-if="item.price[1]">{{-item.price[1]}}</block> 元/月
						</view>
					</view>
				</view>
			</view>
		</view>
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
				houseList: [{
						id: 0,
						url: 'https://tctyj.cn/oss/apt/img/202208/4fd4feb0-b9df-4095-aa14-a9b3ef380b4f.JPG',
						name: '城北青年公寓',
						district: '高新区',
						stock: 62,
						advantage: ['电梯房', '停车位', '充电桩', '便利店'],
						price: [500, 1200],
					}, {
						id: 1,
						url: 'https://tctyj.cn/oss/apt/img/202209/6a899f3d-3059-4ab1-81af-071db591432b.jpg',
						name: '创业家公寓',
						district: '高新区',
						stock: 62,
						advantage: ['电梯房', '停车位', '充电桩', '便利店'],
						price: [500],
					},
					{
						id: 2,
						url: 'https://tctyj.cn/oss/apt/img/202208/57fd45c1-19da-4023-89f1-368be1342176.JPG',
						name: '创业家公寓',
						district: '高新区',
						stock: 62,
						advantage: ['电梯房', '停车位', '充电桩', '便利店'],
						price: [500],
					},
					{
						id: 3,
						url: 'https://tctyj.cn/oss/apt/img/202208/6728b415-6cb0-4993-82b2-e6995d5a4b8f.JPG',
						name: '万科人才公寓',
						district: '高新区',
						stock: 62,
						advantage: ['电梯房', '停车位', '充电桩', '便利店'],
						price: [500],
					},
				],
				input: {
					keyword: ''
				}

			};
		}
	}
</script>

<style lang="scss">
	* {
		background-color: #ffffff;
	}

	.search {
		display: flex;
		margin: 0 auto;
		justify-content: space-around;
		padding-top: 40rpx;

		.input {
			height: 60rpx;
			width: 80%;
			background-color: #ebebeb;
			border-radius: 50rpx;
			padding: 0 20rpx;
			box-sizing: border-box;
		}

		.input input {

			width: 528rpx;
			height: 100%;
			font-size: 26rpx;
			background-color: #ebebeb;
		}

		.input .placeholder {
			color: #999;
			background-color: #ebebeb;
		}

		.input .iconfont {
			font-size: 35rpx;
			color: #999999;
			background-color: #ebebeb;
		}

		.message {
			width: 70rpx;
			height: 70rpx;

			image {
				width: 100%;
				height: 100%;
			}
		}
	}

	.nav {
		height: 86rpx;
		color: #454545;
		// position: fixed;
		// 滑动吸顶
		position: -webkit-sticky;
		position: sticky;
		top: var(--window-top);
		left: 0;
		width: 100%;
		font-size: 28rpx;
		background-color: #ffffff;
		// margin-top: -50rpx;
		// top: %;
		z-index: 9;

		.item {
			width: 25%;
			text-align: center;
		}

		.item.font-color {
			font-weight: bold;
		}

		.item image {
			width: 15rpx;
			height: 19rpx;
			margin-left: 10rpx;
		}
	}

	.house {
		margin-top: 25rpx;
		padding: 20rpx;

	}


	.house .houseList {
		margin-top: 15rpx;
		padding: 5rpx;

		.item {
			height: 300rpx;
			// background-color: lightyellow;
			margin: 10rpx 10rpx;
			// padding: 20rpx;
			display: flex;
			align-items: center;
			border-bottom: 1rpx solid lightgrey;

			image {
				height: 80%;
				width: 300rpx;
				border-radius: 25rpx;
			}

			.detail {
				margin-left: 20rpx;
				height: 80%;
				width: 350rpx;
				padding: 2rpx;
				display: flex;
				flex-direction: column;

				.name {
					font-size: 38rpx;
					font-family: 'Times New Roman', Times, serif;
					font-weight: bold;
				}

				.district {
					margin: 15rpx 0 20rpx 0;
				}

				.advantage {
					width: 100%;
					height: 80rpx;
					text-align: center;
					display: flex;
					flex-direction: row;
					flex-flow: row wrap;


					.itemn {
						background-color: lightgrey;
						font-size: 18rpx;
						width: 100rpx;
						border-radius: 15rpx;
						margin: 2rpx 5rpx;

					}
				}

				.price {
					margin-top: 20rpx;
					font-size: 35rpx;
					font-weight: bold;
					color: #3491fa;
				}
			}
		}
	}
</style>
