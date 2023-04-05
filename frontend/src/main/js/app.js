const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {utcTime: 0};
  }

  async componentDidMount() {
    const res = await fetch('/info');
    const content = await res.json();
    console.log(content);
    this.setState({utcTime: content.utcTime});
  }

  render() {
    return (
      <div>{this.state.utcTime}</div>
    )
  }
}

ReactDOM.render(
  <App />,
  document.getElementById('react')
)
