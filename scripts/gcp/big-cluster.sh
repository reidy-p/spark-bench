gcloud beta dataproc clusters create $1 --max-age="6h" --worker-machine-type=custom-8-32768 --num-preemptible-workers=2
if [ $? -eq 0 ]; then
    # ssh into cluster and setup some basic things
    gcloud compute ssh $1-m -- 'mkdir spark-bench-custom/target/assembly/ -p; mkdir spark-bench-custom/bin/ -p; sudo apt-get install zsh; sh -c "$(curl -fsSL https://raw.github.com/robbyrussell/oh-my-zsh/master/tools/install.sh)"'
    ./scripts/gcp/gcp-scp-commands.sh $1
fi
