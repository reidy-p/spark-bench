gcloud compute scp ./target/assembly/*.jar $1-m:~/spark-bench-custom/target/assembly/
gcloud compute scp ./bin/spark-bench.sh $1-m:~/spark-bench-custom/bin/
gcloud compute scp ./scripts/spark-bench/* $1-m:~

