import React from 'react';

const LoginPage = () => {
    const handleButtonClick = () => {
        const redirectUri = encodeURIComponent('http://localhost:3001/oauth/redirected/kakao');
        const authUrl = `http://localhost:8080/oauth/kakao?redirectUri=${redirectUri}`;
        window.location.href = authUrl;
    };

    return (
        <div style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh'}}>
            <button
                onClick={handleButtonClick}
                style={{padding: '10px 20px', fontSize: '18px', borderRadius: '5px', cursor: 'pointer'}}
            >
               카카오톡 로그인
            </button>
        </div>
    );
};

export default LoginPage;
