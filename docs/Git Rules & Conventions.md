# 💻Git Rules & Conventions

### ✔️ Commit message

##### 메시지 형식

- type{(detail)}: title (#issue)
- ex. `feat(api): kakao login api를 연결 (#123)`

##### type 종류

| type | 설명 |
| ---- | ---- |
| feat | 새로운 기능 추가 |
| fix | 버그 수정 |
| docs | 문서 수정 |
| style | 코드 변경이 없는 수정(formatting, missing semi colons 등) |
| refactor | 코드 수정 |
| chore | 빌드 관련 수정, 패키지 추가 등 |
| test | 테스트 코드 추가 및 리팩토링 |

##### title 규칙

- 제목은 50자를 넘기지 않는다.
- 마침표를 붙이지 않는다.
- 과거시제를 사용하지 않는다.
- 명령어로 작성한다.

---

### ✔️ Pull Request

##### 초기 설정 
- git clone & fork
- [원격 저장소 연결하기](Remote%20%EC%84%A4%EC%A0%95.md)

##### 루틴
> 주의! fork한 저장소를 원격 저장소의 최신 커밋으로 `fetch & merge` 하고 나서 PR 보내기

- 자신의 브랜치를 생성하고, 본인의 브랜치에서 기능 구현
- PR 보내기 전에 [fetch & merge](Fetch%20%26%20Merge.md) 하기
- PR convention에 맞게 [PR 보내기](PR%20%EB%B3%B4%EB%82%B4%EB%8A%94%20%EB%B0%A9%EB%B2%95.md)

##### PR Conventions

- 제목
    - type{(detail)}: title
    - ex. `feat(login): 로그인 기능을 구현`

- 본문
    - Issue
    - 구현 내용(최대한 자세히 작성하기!)
    - 이미지 (선택사항)
 
