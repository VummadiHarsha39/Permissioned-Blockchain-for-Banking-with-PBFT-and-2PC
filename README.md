# A Permissioned Blockchain Simulation for Banking Operations

This project is a simulation of a sharded, permissioned blockchain designed to handle banking operations. It demonstrates key principles of distributed systems, including fault-tolerant consensus, atomic cross-shard transactions, and double-spending prevention.

***

### 🚀 Project Overview

The goal of this project was to design and deploy a proof-of-concept for a high-throughput, fault-tolerant blockchain network for financial services. The simulation runs on a network of **12 servers** organized into **3 distinct clusters**, with each cluster representing a data shard.

The project successfully implements and demonstrates the following core functionalities:

* **Linear PBFT Consensus:** A custom PBFT-style consensus protocol with $O(n)$ complexity that tolerates up to $f$ Byzantine faults per cluster.
* **Atomic Cross-Shard Transactions:** A Two-Phase Commit (2PC) protocol ensures that transactions involving multiple data shards are either fully completed or fully aborted.
* **Double-Spending Prevention:** A pessimistic locking mechanism prevents simultaneous, conflicting transactions from spending the same funds twice.

***

### 🧱 Core Features & Implementation

#### **Distributed Network Architecture**

The simulation models a permissioned network where all participants are known. The network is logically divided into 3 clusters, with 4 nodes per cluster. This sharding is key to demonstrating how a blockchain can scale to handle more transactions by processing them in parallel.

#### **Linear PBFT Consensus**

The heart of the system is a PBFT-style consensus algorithm. It operates in three distinct phases to ensure all honest nodes agree on the order of transactions:

1.  **Pre-prepare Phase:** The leader node proposes a new block to all other nodes (replicas).
2.  **Prepare Phase:** Replicas receive the proposal, and if valid, they broadcast a `Prepare` message to all other nodes.
3.  **Commit Phase:** Replicas collect a quorum ($2f+1$) of `Prepare` messages, then broadcast a `Commit` message. A second quorum of `Commit` messages is required to finalize the block.

#### **Two-Phase Commit (2PC) Protocol**

To handle transactions that move funds between different clusters, we implemented a 2PC protocol. It works as a cooperative agreement:
* **Phase 1 (Prepare-to-Lock):** A coordinator node sends a `PrepareTx` message to all relevant participant nodes, asking if they can process the transaction and to lock the necessary funds.
* **Phase 2 (Commit/Abort):** If all participants vote "YES" and confirm the lock, the coordinator sends a `CommitTx` message, and all nodes apply the transaction and release the lock. If any vote is "NO," the transaction is aborted.

#### **Double-Spending Prevention**

This feature is implemented directly within the 2PC protocol. During the `PrepareTx` phase, a participant node checks if the sender's account has enough funds and if they are not already locked. If the check passes, the account is locked. A second, simultaneous transaction attempting to spend the same funds will find the account locked and will be rejected, successfully preventing a double-spend.

***

### 🛠️ Technical Stack

* **Language:** Java
* **Concurrency:** Multithreading
* **RPC Framework:** gRPC (with Protocol Buffers)
* **Build Tool:** Gradle
* **Core Concepts:** Distributed Systems, Consensus Protocols, Databases (ConcurrentHashMap for in-memory state)

***

### 🖥️ How to Run

1.  Clone this repository to your local machine.
2.  Ensure you have **Java Development Kit (JDK) 17** or higher installed.
3.  Open your terminal in the project's root directory.
4.  Run the following command to compile and execute the project:
    ```bash
    ./gradlew clean run
    ```
5.  Observe the output log. You will see the network initialize, followed by a simulation of a double-spending attempt. The logs will clearly show one transaction succeeding and another failing due to the locking mechanism.

***

### 🙏 Conclusion & Acknowledgments

This project represents the culmination of a wonderful and challenging journey. It demonstrates that with a step-by-step, incremental approach, it's possible to build a complex, multi-component distributed system from scratch. 

### A Small Walkthrough
[![Video](https://vumbnail.com/1129734576.jpg)](https://vimeo.com/1129734576)
