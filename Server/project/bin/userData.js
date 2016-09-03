exports.userDataList = userDataList;

function userDataList(callback){
	return [
		{
			userId: 0,
			email: ["ok2@gmail.com", "ok2@naver.com"],
			nickname: "옥이",
			profile_img: "/image/user/0.png",
			style: ["lovely", "modern"],
			fav_store: ["A1", "B12"],
			fav_reviewer: [0, 3],
			visit_store: ["A1", "B1", "B12", "B15"],
			good_review: [2, 3, 5]
		},
		{
			userId: 1,
			email: ["ttudde@naver.com"],
			nickname: "뚜띠",
			profile_img: "/image/user/1.png",
			style: ["lovely"],
			fav_store: ["B12"],
			fav_reviewer: [0, 3],
			visit_store: ["A1", "B1", "B12", "B15"],
			good_review: [2, 3, 5]
		},
		{
			userId: 2,
			email: ["byels@naver.com"],
			nickname: "별쓰",
			profile_img: "/image/user/2.png",
			style: ["modern"],
			fav_store: ["A1", "B12"],
			fav_reviewer: [0, 3],
			visit_store: ["A1", "B1", "B12", "B15"],
			good_review: [2, 3, 5]
		},
		{
			userId: 3,
			email: ["bro.jo@gmail.com", "bro.jo@naver.com"],
			nickname: "형재",
			profile_img: "/image/user/0.png",
			style: ["lovely", "modern"],
			fav_store: ["A1", "B12", "GS1"],
			fav_reviewer: [0, 3],
			visit_store: ["A1", "B1", "B12", "B15", "DR7"],
			good_review: [2, 3, 5]
		}
	]
}
