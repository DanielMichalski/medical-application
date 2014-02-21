package ui.worker.update;

import util.Worker;

public interface OnUpdateWorkerListener {
    public void insertedNewWorker(Worker worker);

    public void editedWorker(Worker oldWorker, Worker newWorker);

    public void deletedWorker(Worker worker);
}
