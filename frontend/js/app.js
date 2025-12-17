// 섹션 전환 함수
function showSection(sectionName) {
    // 모든 섹션 숨기기
    const sections = document.querySelectorAll('.content-section');
    sections.forEach(section => {
        section.style.display = 'none';
    });

    // 선택한 섹션만 보이기
    const targetSection = document.getElementById(`${sectionName}-section`);
    if (targetSection) {
        targetSection.style.display = 'block';
    }
}

// 회원가입 폼 처리
document.getElementById('register-form').addEventListener('submit', async (e) => {
    e.preventDefault();

    const memberData = {
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        nickname: document.getElementById('nickname').value,
        createdAt: new Date().toISOString()
    };

    try {
        const result = await API.members.register(memberData);
        showResult('register-result', `회원가입 성공! 회원 ID: ${result.userId}`, 'success');
        document.getElementById('register-form').reset();
    } catch (error) {
        showResult('register-result', `회원가입 실패: ${error.message}`, 'error');
    }
});

// 회원 목록 불러오기
async function loadMembers() {
    try {
        const members = await API.members.getAll();
        const listDiv = document.getElementById('members-list');

        if (members.length === 0) {
            listDiv.innerHTML = '<p>등록된 회원이 없습니다.</p>';
            return;
        }

        listDiv.innerHTML = members.map(member => `
            <div class="data-item">
                <h4>회원 ID: ${member.userId}</h4>
                <p><strong>이메일:</strong> ${member.email}</p>
                <p><strong>닉네임:</strong> ${member.nickname || '없음'}</p>
                <p><strong>가입일:</strong> ${new Date(member.createdAt).toLocaleString('ko-KR')}</p>
            </div>
        `).join('');
    } catch (error) {
        showResult('members-list', `회원 목록 로드 실패: ${error.message}`, 'error');
    }
}

// 견종/묘종 목록 불러오기
async function loadBreeds() {
    try {
        const breeds = await API.breeds.getAll();
        const listDiv = document.getElementById('breeds-list');

        if (breeds.length === 0) {
            listDiv.innerHTML = '<p>등록된 견종/묘종이 없습니다.</p>';
            return;
        }

        listDiv.innerHTML = '<h3>견종/묘종 목록</h3>' + breeds.map(breed => `
            <div class="data-item">
                <h4>ID: ${breed.breedId} - ${breed.breedName}</h4>
                <p><strong>종류:</strong> ${breed.species === 'dog' ? '개' : '고양이'}</p>
                <p><strong>평균 수명:</strong> ${breed.avgLifespan || '정보 없음'}년</p>
            </div>
        `).join('');
    } catch (error) {
        document.getElementById('breeds-list').innerHTML =
            `<p class="error">견종/묘종 목록 로드 실패: ${error.message}</p>`;
    }
}

// 펫 등록 폼 처리
document.getElementById('pet-form').addEventListener('submit', async (e) => {
    e.preventDefault();

    const userId = document.getElementById('pet-user-id').value;
    const breedId = document.getElementById('pet-breed').value;

    const petData = {
        name: document.getElementById('pet-name').value,
        birthDate: document.getElementById('pet-birth').value || null,
        gender: document.getElementById('pet-gender').value || null,
        weightKg: document.getElementById('pet-weight').value || null,
        breed: breedId ? { breedId: parseInt(breedId) } : null
    };

    try {
        const result = await API.pets.register(userId, petData);
        showResult('pet-result', `펫 등록 성공! 펫 ID: ${result.petId}`, 'success');
        document.getElementById('pet-form').reset();
    } catch (error) {
        showResult('pet-result', `펫 등록 실패: ${error.message}`, 'error');
    }
});

// 알러지 추가 폼 처리
document.getElementById('allergy-form').addEventListener('submit', async (e) => {
    e.preventDefault();

    const petId = document.getElementById('allergy-pet-id').value;

    const allergyData = {
        ingredientId: parseInt(document.getElementById('allergy-ingredient-id').value),
        severity: document.getElementById('allergy-severity').value
    };

    try {
        const result = await API.allergies.add(petId, allergyData);
        showResult('allergy-result', `알러지 추가 성공!`, 'success');
        document.getElementById('allergy-form').reset();
    } catch (error) {
        showResult('allergy-result', `알러지 추가 실패: ${error.message}`, 'error');
    }
});

// 사료 등록 폼 처리
document.getElementById('food-form').addEventListener('submit', async (e) => {
    e.preventDefault();

    const ingredientsInput = document.getElementById('food-ingredients').value;
    const ingredientIds = ingredientsInput
        ? ingredientsInput.split(',').map(id => parseInt(id.trim())).filter(id => !isNaN(id))
        : [];

    const foodData = {
        name: document.getElementById('food-name').value,
        brand: document.getElementById('food-brand').value || null,
        price: document.getElementById('food-price').value || null,
        ingredientIds: ingredientIds
    };

    try {
        const result = await API.foods.register(foodData);
        showResult('food-result', `사료 등록 성공! 사료 ID: ${result.foodId}`, 'success');
        document.getElementById('food-form').reset();
    } catch (error) {
        showResult('food-result', `사료 등록 실패: ${error.message}`, 'error');
    }
});

// 사료 성분 조회
async function searchFoodIngredients() {
    const foodId = document.getElementById('food-id-search').value;

    if (!foodId) {
        alert('사료 ID를 입력해주세요.');
        return;
    }

    try {
        const ingredients = await API.foods.getIngredients(foodId);
        const resultDiv = document.getElementById('food-ingredients-result');

        if (ingredients.length === 0) {
            resultDiv.innerHTML = '<p>성분 정보가 없습니다.</p>';
            return;
        }

        resultDiv.innerHTML = '<h4>사료 성분 목록</h4>' +
            Array.from(ingredients).map(item => `
            <div class="data-item">
                <p><strong>성분:</strong> ${item.ingredient?.name || '정보 없음'}</p>
                <p><strong>함량:</strong> ${item.percentageAmount || '정보 없음'}%</p>
            </div>
        `).join('');
    } catch (error) {
        document.getElementById('food-ingredients-result').innerHTML =
            `<p class="error">성분 조회 실패: ${error.message}</p>`;
    }
}

// AI 추천 폼 처리
document.getElementById('recommend-form').addEventListener('submit', async (e) => {
    e.preventDefault();

    const petInfo = document.getElementById('pet-info').value;

    try {
        const resultDiv = document.getElementById('recommend-result');
        resultDiv.className = 'result info';
        resultDiv.innerHTML = '<p>AI가 추천을 생성하는 중입니다...</p>';
        resultDiv.style.display = 'block';

        const recommendation = await API.recommend.getRecommendation(petInfo);

        resultDiv.innerHTML = `
            <h3>AI 추천 결과</h3>
            <pre>${recommendation}</pre>
        `;
    } catch (error) {
        showResult('recommend-result', `추천 실패: ${error.message}`, 'error');
    }
});

// 페이지 로드 시 첫 번째 섹션 표시
document.addEventListener('DOMContentLoaded', () => {
    showSection('register');
});
