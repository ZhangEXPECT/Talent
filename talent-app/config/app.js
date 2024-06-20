let domain = 'http://localhost:8899';

module.exports = {
	HTTP_REQUEST_URL: domain,
	HEADER: {
		'content-type': 'application/json'
	},
	HEADERPARAMS: {
		'content-type': 'application/x-www-form-urlencoded'
	},
	// 回话密钥名称 请勿修改此配置
	TOKENNAME: 'Authori-zation',
	// 缓存时间 0 永久
	EXPIRE: 0,
	//分页最多显示条数
	LIMIT: 10
};
