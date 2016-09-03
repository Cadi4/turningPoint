exports.storeDataList = storeDataList;
function storeDataList(callback){
	return [
		{
			id: "GS1",
			name: ["media", "미디어포레", "미디어포래", "mediaf"],
			location: {
				x1: 252,
				x2: 288,
				y1: 342,
				y2: 422
			},
			info: {
				goods: "selling goods",
				kind: "girlcrush",
				style: "street",
				price: "10000",
				card: 1,
				refund: 1,
				exchange: 0,
				fitting: 2,
				open: 900,
				close: 2400,
				holiday: ["sat", "sun"],
				score : 2.0,
				etc : ''
			}
		},
		{
			id: "DR11",
			name: ["11번출구", "11번", "door11", "11gate", "gate11"],
			location: {
				x1: 890,
				x2: 909,
				y1: 480,
				y2: 500
			},
			info: {
				score : 1.0,
			}
		},
		{
			id: "DR7",
			name: ["7번출구", "7번", "door7", "7gate", "gate7"],
			location: {
				x1: 74,
				x2: 89,
				y1: 255,
				y2: 284
			},
			info: {
				score : 3.5,
			}
		},
		{
			id: "DR12",
			name: ["12번출구", "12번", "door12", "12gate", "gate12"],
			location: {
				x1: 555,
				x2: 571,
				y1: 904,
				y2: 925
			},
			info: {
				score : 4.8,
			}
		},
		{
			id: "DR99",
			name: ["이즈타워", "이즈타워출구", "istower"],
			location: {
				x1: 721,
				x2: 776,
				y1: 550,
				y2: 582
			}
		}
	];
}
