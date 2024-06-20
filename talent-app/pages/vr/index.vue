<template>
	<view>
		<web-view :src="houseUrl"></web-view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				houseUrl:'',
				houseId:0,
			};
		},
		onLoad:function(options){
			this.houseId  =options.houseId;
			this.houseUrl=  "http://192.168.248.142:8081";
		},
		
		methods: {
			name() {
				
			}
		},
	}
</script>

<style lang="scss">

</style>
