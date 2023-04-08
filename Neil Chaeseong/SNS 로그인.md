# Android SNS Login Oauth2 (AccessToken 발급)

## Naver (Fragment)
1. naver developer 어플리케이션 등록
2. 라이브러리 설정 (dependency 및 naver sdk 설정)
- https://developers.naver.com/docs/login/android/android.md
3. 네아로 SDK 초기화
```
        NaverIdLoginSDK.initialize(requireContext(), NAVER_CLIENT_ID, NAVER_CLIENT_SECRET, "앱 이름")
```
4. Callback 설정 (성공 시 accessToken 가져오기)
```
        val oAuthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                NidOAuthLogin().callProfileApi(object : NidProfileCallback<NidProfileResponse> {
                    override fun onSuccess(result: NidProfileResponse) {
						// AccessToken 가져오기
                        val naverAccessToken = NaverIdLoginSDK.getAccessToken()
                    } // End of onSuccess

                    override fun onError(errorCode: Int, message: String) {
                        NidOAuthLogin().callDeleteTokenApi(
                            requireContext(),
                            object : OAuthLoginCallback {
                                override fun onError(errorCode: Int, message: String) {
                                    onFailure(errorCode, message)
                                }

                                override fun onFailure(httpStatus: Int, message: String) {
                                }

                                override fun onSuccess() {
                                }

                            })

                        requireView().showSnackBarMessage("네이버 로그인에 실패했습니다.")
                        binding.progressbar.isVisible = false
                    }

                    override fun onFailure(httpStatus: Int, message: String) {
                    }
                })
            }

            override fun onError(errorCode: Int, message: String) {
                requireView().showSnackBarMessage("네이버 로그인에 실패했습니다.")
                binding.progressbar.isVisible = false
            }

            override fun onFailure(httpStatus: Int, message: String) {
                requireView().showSnackBarMessage("네이버 로그인에 실패했습니다.")
                binding.progressbar.isVisible = false
            }
        }
```
5. 네이버 로그인 인증
```
        NaverIdLoginSDK.authenticate(requireContext(), oAuthLoginCallback)
```

## Github (Fragment)
1. Github - Setting-Developer Setting - Oauth Apps 설정
- 어플리케이션 이름, Hompage Url, Callback URL 설정
2. 깃허브 로그인 실행 코드 (인터넷 로그인)
```
		val githubLoginUri = Uri.Builder().scheme("https").authority("github.com")
			.appendPath("login")
			.appendPath("oauth")
			.appendPath("authorize")
			.appendQueryParameter("client_id", BuildConfig.GITHUB_CLIENT_ID)
			.build()
			
        startActivity(Intent(Intent.ACTION_VIEW, githubLoginUri).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        })
```
3. 액티비티 콜백 부분 (accessToken 발급)
```
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uri = intent?.data
        // Oauth 로그인 인지 아닌지 확인
        when (uri?.scheme.toString()) {
            "Callback URL scheme" -> { // 깃허브
                Log.d("ssafy/login/github/body", uri?.getQueryParameter("scope") ?: "")
                loginActivityViewModel.isTriedGithubLogin = true
                loginActivityViewModel.githubCode = uri?.getQueryParameter("code") ?: ""
            }
            else -> {}
        } // End of when
    }
```

# 참고자료
1. 네이버 Oauth2 개발가이드
- https://developers.naver.com/docs/login/android/android.md
2. Github Oauth2 개발가이드
https://docs.github.com/en/apps/oauth-apps/building-oauth-apps/authorizing-oauth-apps
