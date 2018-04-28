# sidechannel-timing-tool
Small tool for measuring system latency during string comparison.

The tool supports three modes, in-vm, tcp and telnet.

The tool works by using a password that is known in advance. Starts by attempting to authenticate using the first letter of the password. 
Then increases the number of letters included in each authentication request over time. Each request produces some timing information
that can be used to determine if the system is vulnerable or not. 

The following examples are taken from an in-vm comparison, this proves that the data is available in the best conditions. As network
equipment improves over time and cloud hosted machines has gained popularity over the last years, this is a real attack vector.

Sample of a measurement that does not leak any information, uses 1:1 byte comparison as implemented in openSSL.
![alt text](https://raw.githubusercontent.com/codingchili/sidechannel-timing-tool/master/safe.png "Current snapshot version")

Sample of a measurement that leaks timing information, allows optimizations and returns early on first mismatching byte.
![alt text](https://raw.githubusercontent.com/codingchili/sidechannel-timing-tool/master/vulnerable.png "Current snapshot version")
This could be used to incrementally guess the password one character by one.

This applies to passwords, even more so if the passwords are in plaintext or not salted appropriately. This method could probably
also be used for tokens - where valid tokens are cached and not revalidated. This attack vector is very tempting in multi-tenant
hosting solutions. If a vulnerable VM is running in the cloud, you could deploy your own VM to that cloud for a small
fee. Depending on the network layout the jitter will be minimal. Jitter makes it harder to deploy this attack, attempts to 
induce random delays are probably very deterministic and easy to analyze.



For more information read the co-authored [report](https://github.com/codingchili/sidechannel-timing-tool/raw/master/Side-Channel%20Attack%20-%20paper.pdf).
