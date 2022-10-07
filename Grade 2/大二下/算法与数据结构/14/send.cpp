#include <bits/stdc++.h>
using namespace std;
# define INF 2147483647
 
vector<vector<pair<int, int>>> dis_list;
vector<string> world_map;
 
 
 
int N, M, x, y, x_end, y_end;
bool fail_flag = false;
int pre[1000001];
 
int get_index(int x, int y) {
    return x * M + y + 1;
}
 
 
void data_input() {
    cin >> N >> M >> x >> y >> x_end >> y_end;
 
 
    dis_list.resize(M * N + 1);
 
    for (int i = 0; i < N; ++i) {
        string tmp;
        cin >> tmp;
        for (int j = 0; j < M; ++j) {
            switch (tmp[j]) {
            case '.':
                tmp[j] = 1;
                break;
            case 'W':
                tmp[j] = 2;
                break;
            case '#':
                tmp[j] = 0;
                break;
            }
        }
        world_map.push_back(tmp);
    }
 
 
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
 
            if (world_map[i][j] == 0) continue;
 
            if (i + 1 != N and world_map[i + 1][j] != 0) {
                dis_list[get_index(i, j)].emplace_back(get_index(i + 1, j), world_map[i + 1][j]);
                dis_list[get_index(i + 1, j)].emplace_back(get_index(i, j), world_map[i][j]);
            }
 
            if (j + 1 != M and world_map[i][j + 1] != 0) {
                dis_list[get_index(i, j)].emplace_back(get_index(i, j + 1), world_map[i][j + 1]);
                dis_list[get_index(i, j + 1)].emplace_back(get_index(i, j), world_map[i][j]);
            }
        }
    }
 
    world_map.clear();
}
 
void do_dijkstra(int index, int end) {
    priority_queue< pair<int, int>, vector <pair<int, int>>, greater<pair<int, int>> > pq;
    vector<int> dis(M * N + 1, INF);
    vector<bool> visited(M * N + 1, false);
    pq.push({ 0, index });
    dis[index] = 0;
    pre[index] = -1;
 
    while (!pq.empty()) {
        int u = pq.top().second;
        pq.pop();
 
        if (visited[u]) continue;
 
        visited[u] = true;
        for (auto x : dis_list[u]) {
            int v = x.first;
            int weight = x.second;
            if (dis[v] > dis[u] + weight) {
                dis[v] = dis[u] + weight;
                pq.push({ dis[v], v });
 
                pre[v] = u;
            }
        }
    }
    if (dis[end] != INF) {
        cout << dis[end] << endl;
    }
    else {
        cout << -1;
        fail_flag = true;
    }
}
 
void path(int i) {
    if (i == (x - 1) * M + y) {
        return;
    }
    string path = "";
    int tmp = (x_end - 1) * M + y_end;
    for (; i != -1; i = pre[i]) {
        if (tmp - i == 1)
            path.append("E");
        if (tmp - i == -1)
            path.append("W");
        if (tmp - i > 1)
            path.append("S");
        if (tmp - i < -1)
            path.append("N");
        tmp = i;
    }
    reverse(path.begin(), path.end());
    cout << path;
}
 
int main() {
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
 
    data_input();
    do_dijkstra((x - 1) * M + y, (x_end - 1) * M + y_end);
    if (!fail_flag)
    {
        path((x_end - 1) * M + y_end);
    }
    return 0;
}