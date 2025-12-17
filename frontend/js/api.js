// API Base URL
const API_BASE_URL = 'http://localhost:8081/api';

// API 클라이언트
const API = {
    // 회원 관련 API
    members: {
        // 회원 가입
        register: async (memberData) => {
            const response = await fetch(`${API_BASE_URL}/members`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(memberData)
            });

            if (!response.ok) {
                const error = await response.text();
                throw new Error(error);
            }

            return await response.json();
        },

        // 모든 회원 조회
        getAll: async () => {
            const response = await fetch(`${API_BASE_URL}/members`);

            if (!response.ok) {
                throw new Error('회원 목록을 가져오는데 실패했습니다.');
            }

            return await response.json();
        }
    },

    // 펫 관련 API
    pets: {
        // 펫 등록
        register: async (userId, petData) => {
            const response = await fetch(`${API_BASE_URL}/members/${userId}/pets`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(petData)
            });

            if (!response.ok) {
                const error = await response.text();
                throw new Error(error);
            }

            return await response.json();
        }
    },

    // 견종/묘종 관련 API
    breeds: {
        // 모든 견종/묘종 조회
        getAll: async () => {
            const response = await fetch(`${API_BASE_URL}/breeds`);

            if (!response.ok) {
                throw new Error('견종/묘종 목록을 가져오는데 실패했습니다.');
            }

            return await response.json();
        }
    },

    // 알러지 관련 API
    allergies: {
        // 펫 알러지 추가
        add: async (petId, allergyData) => {
            const response = await fetch(`${API_BASE_URL}/pets/${petId}/allergies`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(allergyData)
            });

            if (!response.ok) {
                const error = await response.text();
                throw new Error(error);
            }

            return await response.json();
        }
    },

    // 사료 관련 API
    foods: {
        // 사료 등록
        register: async (foodData) => {
            const response = await fetch(`${API_BASE_URL}/foods`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(foodData)
            });

            if (!response.ok) {
                const error = await response.text();
                throw new Error(error);
            }

            return await response.json();
        },

        // 사료 성분 조회
        getIngredients: async (foodId) => {
            const response = await fetch(`${API_BASE_URL}/foods/${foodId}/ingredients`);

            if (!response.ok) {
                const error = await response.text();
                throw new Error(error);
            }

            return await response.json();
        }
    },

    // AI 추천 관련 API
    recommend: {
        // 사료 추천 받기
        getRecommendation: async (petInfo) => {
            const response = await fetch(`${API_BASE_URL}/recommend?pet=${encodeURIComponent(petInfo)}`);

            if (!response.ok) {
                throw new Error('추천을 받는데 실패했습니다.');
            }

            return await response.text();
        }
    }
};

// 결과 표시 헬퍼 함수
function showResult(elementId, message, type = 'success') {
    const resultDiv = document.getElementById(elementId);
    resultDiv.className = `result ${type}`;
    resultDiv.textContent = message;
    resultDiv.style.display = 'block';

    // 5초 후 자동으로 숨김
    setTimeout(() => {
        resultDiv.style.display = 'none';
    }, 5000);
}

// JSON 데이터를 보기 좋게 표시
function displayJSON(data) {
    return JSON.stringify(data, null, 2);
}
