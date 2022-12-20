# 💻Fetch & Merge

### ✔️ remote 설정 확인

- remote 설정 안 되어 있으면 [remote 설정](Remote%20%EC%84%A4%EC%A0%95.md)하고 다시 오기

---

### ✔️ 원격 저장소에서 최신 commit 내역 가져오기

##### 1) 브랜치 변경

```
$ git checkout main
```

##### 2) fetch

- upstream이라는 이름의 저장소에서 main 브랜치를 가져옴으로써 최신 커밋 내역이 가져와졌다.

```
$ git fetch upstream
```

##### 3) merge

- upstream/master에는 최신 commit 내역이 반영되어 있다. 이 반영사항을 내 local 저장소에 반영하려면 merge를 통해 현재 내 main 브랜치에 병합 

```
$ git merge upstream/main
```

##### 4) push

- 변경 사항을 fork해 온 내 저장소에 최종 반영

```
$ git push origin main
```
