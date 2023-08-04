/*4 4 2 2 4 4
.#..
.W..
##.W
....*/
#include <iostream>
#include <queue>
#include <vector>
#include <stack>
#include <algorithm>
using namespace std;
// n - 行，m - 列
int n, m, sx, sy, ex, ey;
vector<int> distances;
vector<bool> visited;
vector<int> path_help;
int infinity = 2000001;
int index_node = 0;
vector<vector<int>> maps;
vector<struct node *> link_maps;
//此处的xy是matrix里的
struct node
{
    int index;
    struct edge *next;
    struct edge *end;
};
struct edge
{
    int length;
    struct node *next;
    struct edge *neiber;
};
vector<string> split(string s)
{
    vector<string> result;
    while (s.find(" ") != string::npos)
    {
        int p = s.find(" ");
        result.emplace_back(s.substr(0, p));
        s = s.substr(p + 1, s.size());
    }
    result.emplace_back(s);
    return result;
}
void add_end(struct node *n, struct edge *e)
{
    if (n->next == NULL)
    {
        n->next = e;
        n->end = e;
        return;
    }
    struct edge *f = n->end;
    f->neiber = e;
    n->end = e;
}
//输入map的ij
int get_index(int x, int y)
{
    return x * m + y;
}
// i1 j1-link_maps, i j-目标
void set_edges(int i1, int j1, int i, int j)
{
    string direc = "";
    if (maps[i][j] == infinity)
    {
        return;
    }
    struct edge *e = new struct edge;
    e->length = maps[i][j];
    e->neiber = NULL;
    e->next = link_maps[get_index(i, j)];
    add_end(link_maps[get_index(i1, j1)], e);
}
void init_edges()
{
    int k = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (maps[i][j] != infinity)
            {
                if (i - 1 >= 0 && maps[i - 1][j] != infinity)
                {
                    set_edges(i, j, i - 1, j);
                }
                if (i + 1 <= n - 1 && maps[i + 1][j] != infinity)
                {
                    set_edges(i, j, i + 1, j);
                }
                if (j + 1 <= m - 1 && maps[i][j + 1] != infinity)
                {
                    set_edges(i, j, i, j + 1);
                }
                if (j - 1 >= 0 && maps[i][j - 1] != infinity)
                {
                    set_edges(i, j, i, j - 1);
                }
            }
        }
    }
}
// position and length
struct cmp
{
    bool operator()(pair<int, int> a, pair<int, int> b)
    {
        return a.second >= b.second;
    }
};
void print_path(int i){
    if (i == (sx - 1) * m + sy - 1) {
        return;
    }
    string path = "";
    int tmp = (ex - 1) * m + ey - 1;
    for (; i != -1; i = path_help[i]) {
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
    cout << path <<endl;    
}
void start_find_dij()
{
    path_help.resize(n*m+1);
    int r_index = get_index(sx - 1, sy - 1);
    int l_index = get_index(ex - 1, ey - 1);
    distances[r_index] = 0;
    path_help[r_index] = -1;
    priority_queue<pair<int, int>, vector<pair<int, int>>, cmp> que;
    que.push(pair<int, int>(r_index, 0));
    while (!que.empty())
    {
        int index = que.top().first;
        que.pop();
        if (visited[l_index])
        {
            break;
        }
        if (visited[index])
        {
            continue;
        }
        else
        {
            visited[index] = true;
        }
        struct edge *e = link_maps[index]->next;
        while (e != NULL)
        {
            int next_node = e->next->index;
            if (!visited[next_node] && distances[index] + e->length < distances[next_node])
            {
                distances[next_node] = distances[index] + e->length;
                que.push(pair<int, int>(next_node, distances[next_node]));
                path_help[next_node] = index;
            }
            e = e->neiber;
        }
    }
    if (visited[l_index])
    {
        cout << distances[l_index] << endl;
        print_path(l_index);
    }
    else
    {
        cout << -1 << endl;
    }
}

void init_vectors()
{
    vector<int> distances_init(n * m, infinity);
    vector<bool> visited_init(n * m, false);
    distances = distances_init;
    visited = visited_init;
}
int main()
{
    string nums;
    getline(cin, nums);
    vector<string> all_nums = split(nums);
    n = stoi(all_nums[0]);
    m = stoi(all_nums[1]);
    sx = stoi(all_nums[2]);
    sy = stoi(all_nums[3]);
    ex = stoi(all_nums[4]);
    ey = stoi(all_nums[5]);
    init_vectors();
    // struct node* path = new struct node[n*m];
    string line;
    for (int i = 0; i < n; i++)
    {
        vector<int> v;
        getline(cin, line);
        for (int j = 0; j < m; j++)
        {
            switch (line[j])
            {
            case '.':
                v.emplace_back(1);
                break;
            case 'W':
                v.emplace_back(2);
                break;
            case '#':
                v.emplace_back(infinity);
                int index = get_index(i, j);
                visited[index] = true;
                break;
            }
            struct node *dot = new struct node;
            dot->index = index_node;
            index_node++;
            dot->next = NULL;
            dot->end = NULL;
            link_maps.emplace_back(dot);
        }
        maps.emplace_back(v);
    }

    if ((n == 1 && m == 1) || (ex == sx && ey == sy))
    {
        cout << 0 << endl;
        return 0;
    }
    init_edges();
    if (maps[ex - 1][ey - 1] == infinity || maps[sx - 1][sy - 1] == infinity)
    {
        cout << -1 << endl;
    }
    else
    {
        maps.clear();
        start_find_dij();
    }
    return 0;
}