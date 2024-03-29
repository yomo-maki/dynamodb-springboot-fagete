# dynamodb-springboot-fagete

## 概要説明

### SpringBoot - DynamoDBの構成をFageteで動かす
SpringBoot - DynamoDBの構成のrestAPIアプリをAWS ECS-Fageteにデプロイして動作させる  
今回ちょっとだけデプロイして確認する程度の作業を行いたいためAWS側の細かい設定が不要なcopilotを使用する

copilot  
ECSで行う操作をCLIのコマンド操作にて、行えるようにしたもの  
ECSを含むアプリに必要なリソース群をコマンドを叩くだけで簡単に構築できるようにしたもの。  

### 環境情報  
SpringBoot 3.1.8  
Gradle 8.5  
Java 17  
Docker  

### 必要な準備  
aws cli, copilotのインストール
AWSアカウントの準備  
DynamoDBに接続する場合、application.propertiesにアクセスキー、シークレットキーを設定  
DynamoDBにテーブル作成を行っておくこと

### 手順
1. 対象となるDockerfileの存在するディレクトリにて以下コマンドを実行  
注意：事前にaws cliにてログインを行っておくこと
```
copilot init
```  


2. コマンドが走ると以下の設定が問われるためそれぞれ設定していく
```
例
・Application name: <任意のアプリ名>
・Workload type: Load Balanced Web Service
・Service name: <任意のサービス名>
・Dockerfile: ./Dockerfile
・Environment: <任意の環境（例:devなど）>
・Port: 8080(デフォルトは80)
```  
注意  
javaの場合は8080 デフォルトで80のままとなると、eniの生成時にエラーが発生しデプロイ失敗となるため注意  

3. 10~20分ほどでデプロイ完了するのでエラーなくできた場合は動作確認などを行う  
作成中、デプロイ完了後にコンソール確認を行うとECRに対象のイメージがプッシュされ、そこから自動的にクラスタ作成、タスク作成、サービス作成されていることが見えます。  
Fagete自体がEC2を元に起動されており、そこにつながるALBなども生成されている。  

4. 今回はRestAPIをデプロイしたためAWSコンソールのECS＞クラスタ＞サービスにてDNSを確認しそこに向けてAPIを叩く。


5. 動作確認後、デプロイしたサービスを削除する  
・起動中のアプリを確認するコマンド
```
copliot app -ls
```
・起動中のアプリを削除するコマンド
```
copliot app delete —name <起動中のサービス名>
```
叩くと消しますか？との選択肢が出るのでyを選んでしばらくすると消える。

コマンド後、コンソール確認すると全ての構成が消えていることを確認できるため今回のようなちょっとした動作確認したい場合、
サービスの消し忘れによる余計な費用発生がなくなるため使いやすい。

# 補足
実際にcopliotを使わない場合、ざっくりでも以下の手順が必要になるのと同時に、④〜の手順に関しては細かい設定項目の設定が必要となる。  
①アプリを作成する  
②Dockerイメージにする  
③ECRにプッシュする  
④ECRタスク実行ロールを作成または確認編集  
⑤関連リソース群（VPC、サブネット、SG、ALBなど）の作成  
⑥ECSクラスター、タスク、サービスを作成  

