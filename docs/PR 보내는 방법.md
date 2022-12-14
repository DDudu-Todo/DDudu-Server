# 💻PR 보내는 방법

### ✔️ branch 생성

- 기본 branch는 main이지만, 개발 용도로 새로운 branch 생성
- branch 생성하면 위치도 새로 만든 branch도 변경됨
```
$ git checkout -b 브랜치명
```

### ✔️ local 환경에서 코드 수정 후 commit

- 코드를 수정한 후 `git add` & `commit`

```
$ git add .
$ git commit -m "커밋 메시지"
```

### ✔️ push

- 현재 branch에서, fork해 온 내 저장소에 push
```
$ git push origin 브랜치명
```

### ✔️ PR 보내기 전 fetch & merge

### ✔️ PR(Pull Request)

- github에서 fork해 온 저장소를 들어가서 `Compare & pull request` 버튼 누르기
- PR 양식대로 내용을 작성하고 `Create pull request` 버튼을 눌러 PR 보내기
- 원본 저장소에서 PR이 승인되면 merge 성공

### ✔️ PR 승인 후 branch 삭제

- PR이 승인되어 원본 저장소에 merge 되면 작업이 끝난 branch 삭제
- 삭제하려는 branch가 아닌 다른 branch(ex. `main`)에서 삭제해야 하므로 다른 branch로 이동
```
$ git checkout main
```

- local 및 remote branch 삭제
```
$ git branch -D 브랜치명
$ git push origin :브랜치명 또는 git push origin --delete 브랜치명
```
